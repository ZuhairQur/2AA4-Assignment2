package ca.mcmaster.se2aa4.island.team51.Action;
import java.util.LinkedList;
import java.util.Queue;

public class ActionSequence {
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

    /**
     * Removes all actions from the queue.
     */
    public void clear() {
        this.queue.clear();
    }

    /**
     * Fills the queue with actions to head to the middle of the map. The actions
     * are: 25 fly, scan, turn right, 25 fly. This is the initial action sequence
     * given to the drone when it is first created.
     */
    private void directToMiddle() {
        for (int i = 0; i < 27; i++) {
            this.queue.add(new Fly());
        }

        this.queue.add(new Turn(TurnSide.RIGHT));

        for (int i = 0; i < 25; i++) {
            this.queue.add(new Fly());
        }

        this.queue.add(new Scan());
    }

    /**
     * Fills the queue with actions to perform a spiral search. The search starts
     * at the middle of the map and spirals outward in a counterclockwise
     * direction. The drone will stop moving and scan the space it is currently in
     * after every move. The drone will stop moving and return to the base at the
     * end of the search.
     */
    public void spiralSearch() {

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

        this.queue.add(new Return());
    }

    /**
     * Turns the drone 90 degrees to the left. The drone will stop
     * moving and scan the space it is currently in before and after
     * the turn.
     */
    private void sharpTurn() {
        this.queue.add(new Scan());
        this.queue.add(new Turn(TurnSide.LEFT));
    }

    /**
     * Performs a wide turn consisting of a sequence of actions where the drone
     * scans the current space, flies forward, scans again, and then turns 90
     * degrees to the left. The drone will stop moving and scan the space before
     * and after the fly action.
     */
    private void wideTurn() {
        this.queue.add(new Scan());
        this.queue.add(new Fly());
        this.queue.add(new Scan());
        this.queue.add(new Turn(TurnSide.LEFT));
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



