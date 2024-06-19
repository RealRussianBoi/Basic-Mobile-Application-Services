import static org.junit.Assert.*;
import org.junit.Test;

public class TaskTest {

    @Test
    public void runAllTests() {
        testGettersAndSetters();
        testNullInputOnID();
        testShortInputOnID();
        testLongInputOnID();
        testNullInputOnName();
        testShortInputOnName();
        testLongInputOnName();
        testNullInputOnDesc();
        testShortInputOnDesc();
        testLongInputOnDesc();
    }

    @Test
    public void testGettersAndSetters() {
        String taskID = "1";
        String taskName = "Jog";
        String taskDescription = "Jogging 5 miles across manhattan.";

        //Test object creation.
        Task task = new Task(taskID, taskName, taskDescription);

        //Test getters.
        assertEquals(taskID, task.getID());
        assertEquals(taskName, task.getName());
        assertEquals(taskDescription, task.getDescription());

        //Test setters and getters.
        String newTaskID = "2";
        String newTaskName = "Jogging";
        String newTaskDescription = "I am tired of jogging. Let's only do 6 miles today.";

        task.setID(newTaskID);
        task.setName(newTaskName);
        task.setDescription(newTaskDescription);

        assertEquals(newTaskID, task.getID());
        assertEquals(newTaskName, task.getName());
        assertEquals(newTaskDescription, task.getDescription());
    }

    @Test
    public void testNullInputOnID() {
        String taskID = null;
        String taskName = "Jog";
        String taskDescription = "Jogging 5 miles across manhattan.";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Task task = new Task(taskID, taskName, taskDescription);
        });
    }

    @Test
    public void testShortInputOnID() {
        String taskID = "";
        String taskName = "Jog";
        String taskDescription = "Jogging 5 miles across manhattan.";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Task task = new Task(taskID, taskName, taskDescription);
        });
    }

    @Test
    public void testLongInputOnID() {
        String taskID = "694206942069420";
        String taskName = "Jog";
        String taskDescription = "Jogging 5 miles across manhattan.";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Task task = new Task(taskID, taskName, taskDescription);
        });
    }

    @Test
    public void testNullInputOnName() {
        String taskID = "1";
        String taskName = null;
        String taskDescription = "Jogging 5 miles across manhattan.";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Task task = new Task(taskID, taskName, taskDescription);
        });
    }

    @Test
    public void testShortInputOnName() {
        String taskID = "1";
        String taskName = "";
        String taskDescription = "Jogging 5 miles across manhattan.";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Task task = new Task(taskID, taskName, taskDescription);
        });
    }

    @Test
    public void testLongInputOnName() {
        String taskID = "1";
        String taskName = "James Bond The Great and Undying Master of Stealth and Murder";
        String taskDescription = "Jogging 5 miles across manhattan.";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Task task = new Task(taskID, taskName, taskDescription);
        });
    }

    @Test
    public void testNullInputOnDesc() {
        String taskID = "1";
        String taskName = "Walter";
        String taskDescription = null;

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Task task = new Task(taskID, taskName, taskDescription);
        });
    }

    @Test
    public void testShortInputOnDesc() {
        String taskID = "1";
        String taskName = "Wicked";
        String taskDescription = "";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Task task = new Task(taskID, taskName, taskDescription);
        });
    }

    @Test
    public void testLongInputOnDesc() {
        String taskID = "1";
        String taskName = "asjdaksa";
        String taskDescription = "This is a string that exceeds fifty characters. It contains more than fifty characters.";

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Task task = new Task(taskID, taskName, taskDescription);
        });
    }

}

