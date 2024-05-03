package example.bot;

import org.junit.Assert;
import org.junit.Test;

/**
 * Класс с тестами к классу {@link BotLogic}
 */
public class BotLogicTest {

    /**
     * Проверка команды /test.
     * Проверяется то, что бот отвечает пользователю списком вопросов.
     */
    @Test
    public void testCommand() {

        FakeBot bot = new FakeBot();
        BotLogic botLogic = new BotLogic(bot);
        User user = new User(1L);

        botLogic.processCommand(user, "/test");
        Assert.assertEquals("Вычислите степень: 10^2", bot.getLastMessage());

        botLogic.processCommand(user, "any answer");
        Assert.assertEquals("Сколько будет 2 + 2 * 2", bot.getLastMessage());

        botLogic.processCommand(user, "any answer");
        Assert.assertEquals("Тест завершен", bot.getLastMessage());
    }

    /**
     * Проверка команды /notify.
     * Проверяется то, что бот отправит напоминание через указанный период времени.
     */
    @Test
    public void notifyCommand() throws InterruptedException {

        FakeBot bot = new FakeBot();
        BotLogic botLogic = new BotLogic(bot);
        User user = new User(1L);

        botLogic.processCommand(user, "/notify");
        Assert.assertEquals("Введите текст напоминания", bot.getLastMessage());

        botLogic.processCommand(user, "текст напоминания");
        Assert.assertEquals("Через сколько секунд напомнить?", bot.getLastMessage());

        botLogic.processCommand(user, "1");
        Assert.assertEquals("Напоминание установлено", bot.getLastMessage());

        Thread.sleep(1010);
        Assert.assertEquals("Сработало напоминание: 'текст напоминания'", bot.getLastMessage());
    }

    /**
     * Проверка команды /test.
     * Проверяется то, что бот запоминает на какие вопросы был дан неверный ответ
     * и предлагает ответить на них снова.
     */
    @Test
    public void repeatCommand() {

        FakeBot bot = new FakeBot();
        BotLogic botLogic = new BotLogic(bot);
        User user = new User(1L);

        botLogic.processCommand(user, "/test");
        Assert.assertEquals("Вычислите степень: 10^2", bot.getLastMessage());

        botLogic.processCommand(user, "wrong answer");
        Assert.assertEquals("Сколько будет 2 + 2 * 2", bot.getLastMessage());

        botLogic.processCommand(user, "6");
        Assert.assertEquals("Тест завершен", bot.getLastMessage());

        // начинается проверка команды /repeat
        botLogic.processCommand(user, "/repeat");
        Assert.assertEquals("Вычислите степень: 10^2", bot.getLastMessage());

        botLogic.processCommand(user, "100");
        Assert.assertEquals("Тест завершен", bot.getLastMessage());

        botLogic.processCommand(user, "/repeat");
        Assert.assertEquals("Нет вопросов для повторения", bot.getLastMessage());
    }
}