public class Section {
    private String sectionName;
    private String url;
    private SectionType  sectionType;

    public Section(String url){
        this.url = url;
        init();
    }

    /**
     * initializes fields using WebScraper class
     */
    private void init(){
        WebScraper webScraper = WebScraper.getInstance();
        sectionName = url.substring(url.length() - 3);
        sectionType = webScraper.getSectionType(url);
    }

    public String getURL(){
        return url;
    }

    public String getSectionName(){
        return sectionName;
    }

    public SectionType getSectionType(){ return sectionType;}

    public void printSeatSummary(){
        WebScraper webScraper = WebScraper.getInstance();
        webScraper.printSeatSummaryForSection(url);
    }

    public void printSectionName(){
        System.out.println(sectionName);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Section section = (Section) o;

        if (sectionName != null ? !sectionName.equals(section.sectionName) : section.sectionName != null) return false;
        if (url != null ? !url.equals(section.url) : section.url != null) return false;
        return sectionType == section.sectionType;

    }

    @Override
    public int hashCode() {
        int result = sectionName != null ? sectionName.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (sectionType != null ? sectionType.hashCode() : 0);
        return result;
    }
}

