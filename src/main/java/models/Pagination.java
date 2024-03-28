package models;

import java.util.List;

public class Pagination<E> {

   private int currentPageNumber;
    private int lastPageNumber;
    private int pageSize;
    private long totalRecords;
    private List<E> records;

    public Pagination() {
    }

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(int currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    public int getLastPageNumber() {
        return lastPageNumber;
    }

    public void setLastPageNumber(int lastPageNumber) {
        this.lastPageNumber = lastPageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public List<E> getRecords() {
        return records;
    }

    public void setRecords(List<E> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "currentPageNumber=" + currentPageNumber +
                ", lastPageNumber=" + lastPageNumber +
                ", pageSize=" + pageSize +
                ", totalRecords=" + totalRecords +
                ", records=" + records +
                '}';
    }
}