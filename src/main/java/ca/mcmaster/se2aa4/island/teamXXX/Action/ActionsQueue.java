package ca.mcmaster.se2aa4.island.teamXXX.Action;
import java.util.LinkedList;
import java.util.Queue;

public class ActionsQueue {
    private int spiralSize = 0;
    private Queue<Action> queue = new LinkedList<>();
    /**
     * Fills the queue with actions to head to the middle of the map. The actions
     * are: 25 fly, scan, turn right, 25 fly. This is the initial action sequence
     * given to the drone when it is first created.
     */
    public void fillWithActions() {
        this.directToMiddle();
        this.spiralSearch();
    }

    public void clear() {
        this.queue.clear();
    }

    /**
     * Fills the queue with actions to head to the middle of the map. The actions
     * are: 25 fly, scan, turn right, 25 fly. This is the initial action sequence
     * given to the drone when it is first created.
     */
    public void directToMiddle() {
        for (int i = 0; i < 27; i++) {
            this.queue.add(new Fly());
        }

        this.queue.add(new Scan());
        this.queue.add(new TurnRight());

        for (int i = 0; i < 25; i++) {
            this.queue.add(new Fly());
        }

        this.queue.add(new Scan());
    }

    public void spiralSearch() {

        this.sharpTurn();
        this.sharpTurn();
        this.sharpTurn();
        this.wideTurn();

        // this.queue.add(new Scan());
        // this.queue.add(new TurnLeft());

        // this.queue.add(new Scan());
        // this.queue.add(new TurnLeft());

        // this.queue.add(new Scan());
        // this.queue.add(new TurnLeft());

        // this.queue.add(new Scan());
        // this.queue.add(new Fly());
        // this.queue.add(new Scan());
        // this.queue.add(new TurnLeft());

        // this.queue.add(new Scan());
        // this.queue.add(new TurnLeft());

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 2*i; j++) {
                this.queue.add(new Scan());
                this.queue.add(new Fly());
            }

            this.wideTurn();

            for (int j = 0; j < 2*(i+1); j++) {
                this.queue.add(new Scan());
                this.queue.add(new Fly());
            }

            this.sharpTurn();

            for (int j = 0; j < 2*(i+1); j++) {
                this.queue.add(new Scan());
                this.queue.add(new Fly());
            }

            this.sharpTurn();

            for (int j = 0; j < 2*(i+1); j++) {
                this.queue.add(new Scan());
                this.queue.add(new Fly());
            }

            this.wideTurn();
        }

        // for (int i = 0; i < 5; i++) {
        //     for (int k = 0; k < 3; k++) {
        //         for (int j = 0; j < i; j++) {
        //             this.queue.add(new Scan());
        //             this.queue.add(new Fly());
        //         }

        //         this.queue.add(new Scan());
        //         this.queue.add(new TurnLeft());
        //     }

        //     for (int j = 0; j < this.spiralSize; j++) {
        //         this.queue.add(new Scan());
        //         this.queue.add(new Fly());
        //     }

        //     this.queue.add(new Scan());
        //     this.queue.add(new Fly());
        //     this.queue.add(new Scan());
        //     this.queue.add(new TurnLeft());
        // }

        this.queue.add(new Return());
    }

    private void sharpTurn() {
        this.queue.add(new Scan());
        this.queue.add(new TurnLeft());
    }

    private void wideTurn() {
        this.queue.add(new Scan());
        this.queue.add(new Fly());
        this.queue.add(new Scan());
        this.queue.add(new TurnLeft());
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



