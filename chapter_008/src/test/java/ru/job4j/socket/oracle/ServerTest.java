package ru.job4j.socket.oracle;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {
    private static final String LN = System.getProperty("line.separator");
    private Socket socket = mock(Socket.class);

    private void testServer(String input, String expected) throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        when(this.socket.getInputStream()).thenReturn(in);
        when(this.socket.getOutputStream()).thenReturn(out);
        Server server = new Server(this.socket);
        server.start();
        assertThat(out.toString(), is(expected));
    }

    @Test
    public void whenSendExitThanGetBye() throws IOException {
        testServer("exit", Joiner.on(LN).join("Bye, good luck!", "", ""));
    }

    @Test
    public void whenSendHelloAndExitThanGetHelloAndBye() throws IOException {
        testServer(
                Joiner.on(LN).join("hello", "exit"),
                Joiner.on(LN).join("Hello, dear friend, I'm a oracle.", "",
                        "Bye, good luck!", "", "")
        );
    }

    @Test
    public void whenSendRandomThanGetRandomAnswer() throws IOException {
        testServer(
                Joiner.on(LN).join("unsupported", "exit"),
                Joiner.on(LN).join("Oracle's answer", "",
                        "Bye, good luck!", "", "")
        );
    }

}