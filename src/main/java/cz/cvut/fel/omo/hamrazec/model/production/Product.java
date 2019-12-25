package cz.cvut.fel.omo.hamrazec.model.production;

abstract public class Product {
    protected int completed;
    protected int width;
    protected int height;
    protected int weight;


    public int getWidth() {

        return width;
    }


    public int getHeight() {

        return height;
    }


    public int getWeight() {

        return weight;
    }


    public int getCompleted() {

        return completed;
    }


    public void setCompleted(int completed) {

        this.completed = completed;
    }
}
