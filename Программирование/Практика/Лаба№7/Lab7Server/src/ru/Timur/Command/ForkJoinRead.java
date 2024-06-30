package ru.Timur.Command;

import ru.Timur.ClientData;
import ru.Timur.Command.Command;
import ru.Timur.Command.Storage;
import ru.Timur.Pair;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.util.concurrent.*;

public class ForkJoinRead extends RecursiveTask<DatagramChannel> {
    private SelectionKey key;
    private ExecutorService executorService;
    public ForkJoinRead(SelectionKey key, ExecutorService executorService) {
        this.key = key;
        this.executorService = executorService;
    }

    @Override
    protected DatagramChannel compute() {
        try {
            //System.out.println(Thread.currentThread().getName());
            DatagramChannel datagramChannel = (DatagramChannel) key.channel();
            ByteBuffer bf = ByteBuffer.allocate(4000);

            InetSocketAddress address = (InetSocketAddress)datagramChannel.receive(bf);

            if (bf.position() != 0) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bf.array());
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                Command command = (Command) objectInputStream.readObject();
                Pair<ClientData, InetSocketAddress> attachPair = new Pair<>();
                Future<ClientData> data;

                data = executorService.submit(new CommandExecuteThread(command));

                attachPair.create(data.get(), address);

                datagramChannel.register(key.selector(), SelectionKey.OP_WRITE).attach(attachPair);
                return datagramChannel;
            }
            return null;

        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
