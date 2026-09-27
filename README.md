# Java Socket Client-Server Demo

Bài tập tìm hiểu lập trình **Client-Server bằng Socket trong Java**, dựa theo bài viết:
👉 https://gpcoder.com/3679-xay-dung-ung-dung-client-server-voi-socket-trong-java/

Repo này cài đặt lại toàn bộ các ví dụ trong bài viết, đã **biên dịch và chạy thử thành công**:

| Mô hình | Package | Mô tả |
|---|---|---|
| TCP - Server tuần tự | `com.gpcoder.tcp` | `EchoChatSingleServer` xử lý từng client một |
| TCP - Server song song | `com.gpcoder.tcp` | `EchoChatMultiServer` + `WorkerThread` dùng ThreadPool xử lý nhiều client cùng lúc |
| TCP - Client | `com.gpcoder.tcp` | `EchoChatClient` gửi lần lượt số 0-9, nhận lại từ server |
| UDP (không nối kết) | `com.gpcoder.udp` | `EchoServer` / `EchoClient` trao đổi qua `DatagramSocket`, `DatagramPacket` |
| Multicast | `com.gpcoder.multicast` | `MulticastSender` gửi quảng bá, `MulticastReceiver` join group nhận tin |

> **Lưu ý:** bài gốc dùng port 7 (cổng hệ thống, cần quyền root). Trong repo này mình đổi sang
> port **7777** (TCP/UDP) và **8888** (Multicast) để chạy được với user thường trên mọi máy.

## Cấu trúc thư mục

```
socket-demo/
├── src/com/gpcoder/
│   ├── tcp/
│   │   ├── EchoChatClient.java
│   │   ├── EchoChatSingleServer.java
│   │   ├── EchoChatMultiServer.java
│   │   └── WorkerThread.java
│   ├── udp/
│   │   ├── EchoServer.java
│   │   └── EchoClient.java
│   └── multicast/
│       ├── MulticastSender.java
│       └── MulticastReceiver.java
├── screenshots/          <- ảnh chụp kết quả chạy (xem bên dưới)
└── README.md
```

## Cách biên dịch & chạy

```bash
# Biên dịch toàn bộ
javac -d bin $(find src -name "*.java")

# 1) TCP - Server tuần tự
java -cp bin com.gpcoder.tcp.EchoChatSingleServer
# Mở terminal khác:
java -cp bin com.gpcoder.tcp.EchoChatClient

# 2) TCP - Server song song (mở nhiều client cùng lúc để thấy xử lý song song)
java -cp bin com.gpcoder.tcp.EchoChatMultiServer
java -cp bin com.gpcoder.tcp.EchoChatClient   # chạy vài lần / vài terminal

# 3) UDP
java -cp bin com.gpcoder.udp.EchoServer
java -cp bin com.gpcoder.udp.EchoClient       # gõ message rồi Enter, gõ "exit" để thoát

# 4) Multicast (mở 1 sender + nhiều receiver)
java -cp bin com.gpcoder.multicast.MulticastReceiver
java -cp bin com.gpcoder.multicast.MulticastSender
```

## Kết quả chạy thử (screenshots)

Ảnh chụp màn hình chạy chương trình nằm trong thư mục [`screenshots/`](./screenshots):

1. `01-tcp-single-server.png` / `02-tcp-single-client.png` — TCP server tuần tự & client gửi/nhận số 0-9.
2. `03-tcp-multi-server.png` / `04-tcp-multi-clients.png` — TCP server song song xử lý đồng thời 3 client.
3. `05-udp-server.png` / `06-udp-client.png` — UDP echo server/client trao đổi message qua Datagram.
4. `07-multicast-sender.png` / `08-multicast-receivers.png` — Multicast: 1 sender, 2 receiver join cùng group nhận tin giống nhau.

> Các ảnh trên được chụp từ terminal khi chạy chương trình trong môi trường thử nghiệm.
> Nếu import project vào **Eclipse**, bạn nên chụp thêm ảnh màn hình code + console chạy trong Eclipse
> và bổ sung vào thư mục `screenshots/` để bài nộp đầy đủ hơn.

## Tóm tắt kiến thức

- **TCP (`Socket`, `ServerSocket`)**: có nối kết (connection-oriented), tin cậy, dữ liệu đến đúng thứ tự.
  Quy trình: `socket()` → `bind()`/`listen()` (server) → `connect()`/`accept()` → `read()`/`write()` → `close()`.
- **UDP (`DatagramSocket`, `DatagramPacket`)**: không nối kết, không đảm bảo thứ tự/độ tin cậy, nhưng nhanh hơn TCP,
  phù hợp cho truyền âm thanh/hình ảnh thời gian thực.
- **Multicast (`MulticastSocket`)**: một dạng UDP đặc biệt cho phép 1 sender gửi tới nhiều receiver cùng lúc
  thông qua địa chỉ nhóm lớp D (224.0.0.0 - 239.255.255.255).
- **Server tuần tự vs song song**: server tuần tự chỉ phục vụ 1 client tại 1 thời điểm; server song song
  dùng nhiều thread (ở đây dùng `ExecutorService`/ThreadPool) để phục vụ nhiều client đồng thời.

## Tài liệu tham khảo

- https://gpcoder.com/3679-xay-dung-ung-dung-client-server-voi-socket-trong-java/
- https://docs.oracle.com/javase/tutorial/networking/index.html
