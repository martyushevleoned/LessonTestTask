package example.container;

import org.junit.Assert;
import org.junit.Test;

/**
 * Класс с тестами для {@link Container}
 */
public class ContainerTest {

    /**
     * Проверяет метод {@link Container#add}.
     * Проверяет наличие элемента после каждого добавления.
     * Проверяет отсутствие не добавленных элементов.
     */
    @Test
    public void add() {

        Container container = new Container();

        Item firstItem = new Item(1);
        container.add(firstItem);
        Assert.assertTrue(container.contains(firstItem));
        Assert.assertEquals(1, container.size());

        Item secondItem = new Item(2);
        container.add(secondItem);
        Assert.assertTrue(container.contains(secondItem));
        Assert.assertEquals(2, container.size());

        Item thirdItem = new Item(3);
        Assert.assertFalse(container.contains(thirdItem));
    }

    /**
     * Проверяет метод {@link Container#remove}.
     * Проверяет состав кэша после удаления элемента.
     */
    @Test
    public void remove() {

        Container container = new Container();

        Item firstItem = new Item(1);
        Item secondItem = new Item(2);

        container.add(firstItem);
        container.add(secondItem);
        container.remove(secondItem);

        Assert.assertTrue(container.contains(firstItem));
        Assert.assertFalse(container.contains(secondItem));
        Assert.assertEquals(1, container.size());
    }
}