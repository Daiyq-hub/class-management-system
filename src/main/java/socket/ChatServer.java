public class ChatServer {
    public static void main(String[] args) throws IOException {
        // 或集成到Spring Boot
        ServerSocket server = new ServerSocket(8081);
        Socket client = server.accept();
        new Thread(() -> { // 读线程
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while (true) {
                System.out.println(in.readLine());
            }
        }).start();
        new Thread(() -> { // 写线程
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                out.println(scanner.nextLine());
            }
        }).start();
    }
}
// 客户端类似，使用Socket连接IP:port