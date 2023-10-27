public class item implements configurration
{
    static int nextId =1;
    int id;
    String title;
    int popularityCount;
    private boolean borrowed = false;
    int cost;


    item(String t,int P,int c)
    {
        id = nextId++;
        title = t;
        popularityCount = P;
        this.cost = c;

    }
    public int getcost()
    {
        return cost;
    }
    public int getId()
    {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setpopularity(int p) {
        this.popularityCount = p;
    }
    @Override
    public void display()
    {
        System.out.println(" ID: " + id + "\n" + " Title: " + title + " Popularity: " + popularityCount);
    }
    @Override
    public int calculatecost()
    {
        return cost;
    }
    public int getPopularityCount() {
        return popularityCount;
    }

    public void increasePopularityCount() {
        popularityCount++;
    }
    public boolean isBorrowed() {
        return borrowed;
    }

    public void markAsBorrowed() {
        borrowed = true;
    }


    public String getAuthor() {
        // Return author information based on the item type
        return "No author information available"; // Default implementation
    }

    public int getYear() {
        return 0;
    }
}