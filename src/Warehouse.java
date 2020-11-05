import java.util.*;

/**
 * A Warehouse holds zero or more Articles and can provide information about
 * the Articles. One can add Articles to a Warehouse during its lifetime,
 * reset the Warehouse, create a copy which contains Articles of at least a
 * certain value, and make various queries to the Warehouse. (Thus, the
 * number of Articles that will be stored by a Warehouse object is not yet
 * known when the new object is created, and it may grow and shrink over the
 * lifetime of a Warehouse object.)
 *
 * @author Janos Nagy
 */
public class Warehouse {

    // The articles arraylist holds the list of articles stored in the warehouse. The amount of articles is unknown
    private ArrayList<Article> articles;
    // The string variable "name" holds the name of an article. Must be non-null.
    private String name;
    // The long variable "price" holds the price of an article. Must be non-null.
    private long price;
    /* Constructors */

    /**
     * Constructs a new Warehouse without any Articles.
     */
    public Warehouse() {
        name = "";
        price = 0;
        articles = new ArrayList<>();
    }

    /**
     * Constructs a new Warehouse containing the non-null Articles in articles.
     * The articles array may be modified by the caller afterwards without
     * affecting this Warehouse, and it will not be modified by this
     * constructor.
     *
     * @param articles must not be null; non-null elements are added to the
     *  constructed Warehouse
     */
    public Warehouse(Article[] articles) {
        this();
        for(int i=0 ; i < articles.length ; i++ ){
            if(articles[i] != null){
                this.articles.add(articles[i]);
            }
        }
    }

    /* Modifiers */

    /**
     * Adds an Article e to this Warehouse if e is not null; does not modify this
     * Warehouse otherwise. Returns true if e is not null, false otherwise.
     *
     * @param e an article to be added to this Warehouse
     * @return true if e is not null, false otherwise
     */
    public boolean add(Article e) {
        if(e != null){
            this.articles.add(e);
            return true;
        }
        return false;
    }

    /**
     * Adds all non-null Articles in articles to this Warehouse.
     *
     * @param articles contains the Article objects to be added to
     *  this Warehouse; must not be null (but may contain null)
     * @return true if at least one element of articles is non-null;
     *  false otherwise
     */
    public boolean addAll(Article[] articles) {
        boolean flag = false;
        for(int i=0 ; i < articles.length ; i++){
            if(articles[i] != null){
                this.articles.add(articles[i]);
            }
        }
        if(flag){
            return true;
        }
        return false;
    }

    /**
     * Resets this Warehouse to a Warehouse that contains 0 Articles.
     */
    public void reset() {
        this.articles = new ArrayList<Article>();
    }

    /**
     * Removes certain Articles from this Warehouse. Exactly those Articles
     * are kept whose price in pence is greater than or equal to the specified
     * minimum price in pence.
     *
     * @param minArticlePriceInPence the minimum price in pence for the
     *  Articles that are kept
     */
    public void keepOnlyArticlesWith(int minArticlePriceInPence) {
        //https://www.geeksforgeeks.org/remove-element-arraylist-java/
        Iterator<Article> iterator = this.articles.iterator();
        while(iterator.hasNext()){
            Article articles = iterator.next();
            if(articles.getPriceInPence() < minArticlePriceInPence){
                iterator.remove();
            }

        }
    }

    /* Accessors */

    /**
     * Returns the number of non-null Articles in this Warehouse.
     *
     * @return the number of non-null Articles in this Warehouse
     */
    public int numberOfArticles() {
        return this.articles.size();
    }

    /**
     * Returns the total price of the Articles in this Warehouse.
     *
     * @return the total price of the Articles in this Warehouse.
     */
    public int totalPriceInPence() {
        int totalPrice = 0;
        for(Article element : this.articles){
            totalPrice += element.getPriceInPence();
        }
        return totalPrice;
    }

    /**
     * Returns the average price in pence of the (non-null) Articles
     * in this Warehouse. In case there is no Article in this Warehouse,
     * -1.0 is returned.
     *
     * For example, if this Warehouse has the contents
     *   new Article("Soda", 400)
     * and
     *   new Article("Water", 395),
     * the result is: 397.5
     *
     * @return the average price of the Articles in this Warehouse,
     *  or -1.0 if there is no such Article.
     */
    public double averagePriceInPence() {
        double total = 0.0;
        for(Article element : this.articles){
            if(element != null){
                total += element.getPriceInPence();
            }
        }
        if(total == 0){
            return -1.0;
        }
        return total / articles.size();
    }

    /**
     * Returns the most expensive Article in this Warehouse;
     * null if this Warehouse does not contain any Article objects.
     * If several Articles have the same maximum price, an arbitrary
     * of these Articles will be returned.
     *
     * @return the most expensive Article in this Warehouse;
     *  null if this Warehouse does not contain any Article objects
     */
    public Article mostExpensiveArticle() {
        Article mostExpensiveArticle = new Article("", 0);
        if (this.articles.isEmpty()) {
            return null;
        }
        for(Article element : this.articles){
            if(mostExpensiveArticle.getPriceInPence() < element.getPriceInPence()){
                mostExpensiveArticle = element;
            }
        }
        return mostExpensiveArticle;
    }

    /**
     * Returns a new Warehouse with exactly those Articles of this Warehouse
     * whose price is greater than or equal to the specified method parameter.
     * Does not modify this Warehouse.
     *
     * @param minArticlePriceInPence the minimum price in pence for the
     *  Articles in the new Warehouse
     * @return a new Warehouse with exactly those Articles of this Warehouse
     *  whose price is greater than or equal to the specified method parameter
     */
    public Warehouse makeNewWarehouseWith(int minArticlePriceInPence) {
        Warehouse newWarehouse = new Warehouse();
        for(Article element : this.articles){
            if(minArticlePriceInPence <= element.getPriceInPence()){
                newWarehouse.articles.add(element);
            }
        }
        return newWarehouse;
    }

    /**
     * Returns a string representation of this Warehouse. The string
     * representation consists of a list of the Warehouse's contents,
     * enclosed in square brackets ("[]"). Adjacent Articles are
     * separated by the characters ", " (comma and space). Articles are
     * converted to strings as by their toString() method. The
     * representation does not mention any null references.
     *
     * So for
     *
     * Article a1 = new Article("Pen", 750);
     * Article a2 = null;
     * Article a3 = new Article("Stamp", 80);
     * Article[] articles = { a1, a2, a1, a3 };
     * Warehouse w = new Warehouse(articles);
     *
     * the call w.toString() will return one of the three following Strings:
     *
     * "[(Pen, 750), (Pen, 750), (Stamp, 80)]"
     * "[(Pen, 750), (Stamp, 80), (Pen, 750)]"
     * "[(Stamp, 80), (Pen, 750), (Pen, 750)]"
     *
     * @return a String representation of this Warehouse
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String content = "";
        if(this.articles.size()==0){
            return "[]";
        }
        for(int i = 0; i < articles.size(); i++){
            if(articles.get(i)!=null){
                content += articles.get(i).toString();}
            if(i<this.articles.size() - 1){
                content +=", ";}
        }
        return "[" + content + "]";
    }

    /* class methods */

    /**
     * Class method to return a Warehouse with the highest total price from an
     * array of Warehouses. If we have an array with a Warehouse of 3000 pence
     * and a Warehouse with 4000 pence, the Warehouse with 4000 pence is
     * returned.
     *
     * Entries of the array may be null, and your method should work also in
     * the presence of such entries. So, if in the above example we had an
     * additional third array entry null, the result would be exactly the same.
     *
     * If there are several Warehouses with the same price, it is up to the
     * method implementation to choose one of them as the result (i.e., the
     * choice is implementation-specific, and method users should not rely on
     * any particular behaviour).
     *
     * @param warehouses must not be null, but may contain null
     * @return one of the Warehouses with the highest total price among all
     *  Warehouses in the parameter array; null if there is no non-null
     *  reference in warehouses
     */
    public static Warehouse mostValuedWarehouse(Warehouse[] warehouses) {
        Warehouse mostValued = new Warehouse();
        if(warehouses.length == 0){
            return null;
        }
        for(Warehouse element : warehouses){
            if(element.totalPriceInPence() > mostValued.totalPriceInPence() ){
                mostValued = element;
            }
        }
        return mostValued;
    }
}