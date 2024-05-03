package example.bot;

public class FakeBot implements Bot{

    private String lastMessage = null;

    @Override
    public void sendMessage(Long chatId, String message) {
        lastMessage = message;
    }

    public String getLastMessage() {
        return lastMessage;
    }
}
