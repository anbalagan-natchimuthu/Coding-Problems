package interview.Algorithm;
import java.util.*;

/**
 * http://www.techiedelight.com/job-sequencing-problem-deadlines/
 * https://www.geeksforgeeks.org/job-sequencing-problem-set-1-greedy-algorithm/
 * Given an array of jobs where every job has a deadline and associated profit
 * if the job is finished before the deadline. It is also given that every job takes single unit of time,
 * so the minimum possible deadline for any job is 1.
 * How to maximize total profit if only one job can be scheduled at a time.
 * Examples:

 Input: Four Jobs with following deadlines and profits
 JobID    Deadline      Profit
 a        4            20
 b        1            10
 c        1            40
 d        1            30
 Output: Following is maximum profit sequence of jobs
 c, a


 Input:  Five Jobs with following deadlines and profits
 JobID     Deadline     Profit
 a         2           100
 b         1           19
 c         2           27
 d         1           25
 e         3           15
 Output: Following is maximum profit sequence of jobs
 c, a, e
 */
// data structure to store jobs info. Each job has an identifier,
// a deadline and profit associated with it
class Jobs
{
    private int taskID, deadline, profit;

    public Jobs(int taskID, int deadline, int profit) {
        this.taskID = taskID;
        this.deadline = deadline;
        this.profit = profit;
    }

    public int getDeadline() {
        return deadline;
    }

    public int getProfit() {
        return profit;
    }

    public int getTaskID() {
        return taskID;
    }
};

class Scheduler
{
    // schedule jobs to maximize profit
    public static int scheduleJobs(List<Jobs> jobs, int T)
    {
        // stores maximum profit that can be earned by scheduling jobs
        int profit = 0;

        // array to store used and unused slots info
        int[] slot = new int[T];
        Arrays.fill(slot, -1);

        // consider each job in decreasing order of their profits
        for (Jobs job: jobs)
        {
            // search for next free slot and map the task to that slot
            for (int j = job.getDeadline() - 1; j >= 0; j--)
            {
                if (j < T && slot[j] == -1)
                {
                    slot[j] = job.getTaskID();
                    profit += job.getProfit();
                    break;
                }
            }
        }

        // print the scheduled jobs
        System.out.println("The Scheduled jobs are - ");
        Arrays.stream(slot)
            .filter(val -> val != -1)
            .forEach(System.out::println);

        // return total profit that can be earned
        return profit;
    }

    public static void main(String[] args)
    {
        // List of given jobs. Each job has an identifier, a deadline and
        // profit associated with it
        List<Jobs> jobs = Arrays.asList(
            new Jobs(1, 9, 15), new Jobs(2, 2, 2),
            new Jobs(3, 5, 18), new Jobs(4, 7, 1),
            new Jobs(5, 4, 25), new Jobs(6, 2, 20),
            new Jobs(7, 5, 8), new Jobs(8, 7, 10),
            new Jobs(9, 4, 12), new Jobs(10, 3, 5)
        );

        // stores maximum deadline that can be associated with a job
        final int T = 15;

        // arrange the jobs in decreasing order of their profits ( sort profit descending)
        Collections.sort(jobs, (a, b) -> b.getProfit() - a.getProfit());

        // schedule jobs and calculate maximum profit
        System.out.println("\nTotal Profit is: " + scheduleJobs(jobs, T));
    }
}