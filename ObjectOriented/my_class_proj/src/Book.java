public class Book implements Cloneable {
    private String title;
    private String author;
    private float price;

    public Book(String tmpTitle, String tmpAuthor, float tmpPrice) {
        //TODO Auto-generated constructor stub
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public float getPrice() {
        return this.price;
    }

    public String toString() {
        return "Title - " + this.title + "\nAuthor - "
                + this.author + "\nPrice - " + String.format("%.2f", this.price);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}