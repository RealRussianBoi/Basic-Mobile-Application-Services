import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskServiceTest {
    private TaskService taskService;
    ArrayList<Task> tasks = new ArrayList<>();
    private InputStream originalSystemIn;

    @Before
    public void setUp() {
        // Initialize the contact service before each test
        taskService = new TaskService();
        originalSystemIn = System.in;
        // You can initialize any required resources here
    }

    @Test
    public void runTaskTest() {
        TaskTest taskTest = new TaskTest();
        taskTest.runAllTests();
    }

    @Test
    public void testAddTask() {
        String input = "Jog\n5 Miles\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scnr = new Scanner(System.in);
        Task task = TaskService.addNewTask(scnr, tasks);

        assertNotNull(task);
        assertNotNull(task.getID());
        assertEquals("Jog", task.getName());
        assertEquals("5 Miles", task.getDescription());
    }

    @Test
    public void testDeleteTask() {
        String input = "Jog\n5 Miles\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scnr = new Scanner(System.in);
        Task task = TaskService.addNewTask(scnr, tasks);
        tasks.add(task);

        TaskService.deleteTaskViaID(tasks, "1");
        assertNotNull(tasks);
        assertEquals(0, tasks.size());
    }

    @Test
    public void testUpdateTask() {
        String input = "Jog\n5 Miles\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Scanner scnr = new Scanner(System.in);
        Task task = TaskService.addNewTask(scnr, tasks);
        tasks.add(task);
        scnr.close();

        // Reset System.in to its original state
        System.setIn(originalSystemIn);

        input = "1\nJogging\n1\n5 miles to world trade center.\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        scnr = new Scanner(System.in);
        TaskService.updateTaskViaID(scnr, tasks, "1");

        assertNotNull(tasks);
        assertNotNull(tasks.get(0).getID());
        assertEquals("Jogging", tasks.get(0).getName());
        assertEquals("5 miles to world trade center.", tasks.get(0).getDescription());

        // Close the Scanner again
        scnr.close();
    }

}
