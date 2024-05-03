package example.note;

import org.junit.Assert;
import org.junit.Test;

/**
 * Класс с тестами для {@link NoteLogic}
 */
public class NoteLogicTest {

    /**
     * Проверяет комманду /add.
     * После каждого добавления проверяет наличие записи
     */
    @Test
    public void handleAddMessage() {

        NoteLogic noteLogic = new NoteLogic();

        Assert.assertEquals("Note added", noteLogic.handleMessage("/add first note"));
        Assert.assertEquals("Notes:\nfirst note", noteLogic.handleMessage("/notes"));

        Assert.assertEquals("Note added", noteLogic.handleMessage("/add second note"));
        Assert.assertEquals("Notes:\nfirst note\nsecond note", noteLogic.handleMessage("/notes"));
    }

    /**
     * Проверяет комманду /del.
     * После удаления проверяет отсутствие указанной записи
     */
    @Test
    public void handleDelMessage() {

        NoteLogic noteLogic = new NoteLogic();

        noteLogic.handleMessage("/add first note");
        noteLogic.handleMessage("/add second note");

        Assert.assertEquals("Note deleted", noteLogic.handleMessage("/del 1"));
        Assert.assertEquals("Notes:\nsecond note", noteLogic.handleMessage("/notes"));
    }

    /**
     * Проверяет комманду /edit.
     * После исправления проверяет текст указанной записи
     */
    @Test
    public void handleEditMessage() {

        NoteLogic noteLogic = new NoteLogic();

        noteLogic.handleMessage("/add first note");
        noteLogic.handleMessage("/add second note");

        Assert.assertEquals("Note edited", noteLogic.handleMessage("/edit 1 outdated note"));
        Assert.assertEquals("Notes:\noutdated note\nsecond note", noteLogic.handleMessage("/notes"));
    }
}