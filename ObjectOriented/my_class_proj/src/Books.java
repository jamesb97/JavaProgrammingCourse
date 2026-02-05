public class Books implements Cloneable {
    private String title;
    private String author;
    private float price;

    public Books() {
    }

    public Books(String title, String author, float price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Overriding the clone() method
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
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

    public boolean equals(Book book2) {
        if (this.title.equals(book2.getTitle()) &&
                this.author.equals(book2.getAuthor()) &&
                this.price == book2.getPrice()) {
            return true;
        } else {
            return false;
        }
    }
}