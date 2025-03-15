package ca.mcmaster.se2aa4.island.teamXXX;

import java.util.LinkedList;
import java.util.Queue;

public class ActionsQueue {
    private Queue<Action> queue = new LinkedList<>();
    /**
     * Fills the queue with actions to head to the middle of the map. The actions
     * are: 25 fly, scan, turn right, 25 fly. This is the initial action sequence
     * given to the drone when it is first created.
     */
    public void fillWithActions() {
        this.headToMiddle();
    }


    /**
     * Fills the queue with actions to head to the middle of the map. The actions
     * are: 25 fly, scan, turn right, 25 fly. This is the initial action sequence
     * given to the drone when it is first created.
     */
    private void headToMiddle() {
        for (int i = 0; i < 25; i++) {
            this.queue.add(new Fly());
        }

        this.queue.add(new Scan());
        this.queue.add(new TurnRight());

        for (int i = 0; i < 25; i++) {
            this.queue.add(new Fly());
        }

        this.queue.add(new Scan());
        this.queue.add(new Return());
    }

    /**
     * Retrieves and removes the next action from the queue.
     *
     * @return The next action in the queue.
     */
    public Action getNextAction() {
        return this.queue.remove();
    }


}



