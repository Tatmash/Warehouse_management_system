/**
 * An Article has a name and a price in pence. Objects of this class
 * are immutable: after an object of class Article has been created,
 * one cannot change the values of its attributes. Thus, the class
 * Article has no mutators.
 *
 * @author Carsten Fuhs
 */
public class Article implements Comparable<Article> {

    /** The name of the Article. Always non-null after object creation. */
    private final String name;

    /** The price of an Article in pence. Must be at least 0. */
    private final long priceInPence;

    /**
     * Constructs a new Article with given name and priceInPence.
     *
     * @param name the name of the Article; must not be null
     * @param priceInPence the price of the Article in pence;
     *  must not be negative
     * @throws NullPointerException if name is null
     * @throws IllegalArgumentException if priceInPence is negative
     */
    public Article(String name, long priceInPence) {
        if (name == null) {
            throw new NullPointerException("name must not be null!");
        }
        if (priceInPence < 0) {
            throw new IllegalArgumentException("Expected priceInPence >= 0, found "
                    + priceInPence);
        }
        this.name = name;
        this.priceInPence = priceInPence;
    }

    /**
     * Returns the name of this Article.
     *
     * @return the name; always non-null
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the price in pence of this Article.
     *
     * @return the price in pence; always at least 0
     */
    public long getPriceInPence() {
        return this.priceInPence;
    }

    /**
     * Returns a String representation of this Article with a "(", the name of
     * this Article, ", " (comma and space), the price in pence and ")".
     *
     * For example, if p is an Article with name "Pen" and a price of 750 pence,
     * the call p.toString() would return "(Pen, 750)".
     *
     * @return a human-readable String representation of this Article
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "(" + this.name + ", " + this.priceInPence + ")";
    }

    /**
     * Returns the hash code of this Article. The hash code of an Article is
     * based on its name and its price in pence.
     *
     * @return the hash code of this Article
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        // this implementation relies on the name attribute being non-null
        return prime * this.name.hashCode() + (int) this.priceInPence;
    }

    /**
     * Returns whether this Article and another object are equal. This is the
     * case if the other object is also an Article object with the same price
     * in pence and equal name.
     *
     * @param obj another object to be compared with
     * @return whether this Article and obj are equal
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Article)) {
            return false;
        }
        Article other = (Article) obj;
        // this implementation relies on the name attribute being non-null
        return this.priceInPence == other.priceInPence
                && this.name.equals(other.name);
    }

    /**
     * Compares this Article to o. The comparison considers first the prices
     * (higher is greater) and, in case of a tie, the names wrt their
     * natural ordering given by their compareTo() method.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     *  is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     */
    @Override
    public int compareTo(Article o) {
        if (this.priceInPence > o.priceInPence) {
            return 1;
        }
        if (this.priceInPence < o.priceInPence) {
            return -1;
        }
        // this implementation relies on the name attribute being non-null
        return this.name.compareTo(o.name);
    }
}

