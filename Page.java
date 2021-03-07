
public class Page {
    // ---------------------- fields ----------------------
    private String str;
    private int index;
    // ---------------------- constructors ----------------------
    public Page(String s,int i){
        this.str=s;
        this.index=i;
    }
    // ---------------------- methods ----------------------

    //Setting page data
    public void setPageData(String s){ this.str=s;
    }

    //returns page data
    public String getPageData(){ return this.str;
    }

    //returns page index
    public int getIndex(){ return this.index;
    }
}
