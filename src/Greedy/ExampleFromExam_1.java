package Greedy;
import java.util.Arrays;
import java.util.PriorityQueue;

//First question node_1 written exam 2018-2019 resit.

public class ExampleFromExam_1 {
    public static class Job implements Comparable<Job> {
        int time;
        int deadline;

        public Job(int time, int deadline) {
            this.time = time;
            this.deadline = deadline;
        }

        @Override
        public int compareTo(Job other) {
            return Integer.compare(this.deadline, other.deadline);
        }
    }

    public static class Schedule {
        int start;
        int finish;

        public Schedule(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }
    }

    public static void main(String[] args) {
        int p = 2;
        int n = 4;

        Job[] jobs = new Job[n];
        jobs[0] = new Job(2,1);
        jobs[1] = new Job(3,1);
        jobs[2] = new Job(1,5);
        jobs[3] = new Job(6,4);

        Schedule[] schedules = Solve(p, jobs);

        for (int i=0; i<n; i++) {
            System.out.println(schedules[i].start + "---" + schedules[i].finish);
        }
    }

    public static Schedule[] Solve(int p, Job[] jobs) {
        Arrays.sort(jobs);
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i=0; i<p; i++) queue.add(0);

        Schedule[] schedules = new Schedule[jobs.length];
        int maxLateness = 0;

        for (int i=0; i<jobs.length; i++) {
            int start = queue.remove();
            int finish = start + jobs[i].time;
            queue.add(finish);
            int lateness = Math.max(finish-jobs[i].deadline, 0);
            maxLateness = Math.max(maxLateness, lateness);
            schedules[i] = new Schedule(start, finish);
        }

        System.out.println("Max Lateness: " + maxLateness);
        return schedules;
    }
}
