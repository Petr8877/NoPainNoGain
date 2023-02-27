package finalProject.NoPainNoGain.core.dto;

import java.util.List;

public class PageDTO<T> {

    private int number;

    private int size;

    private int totalPage;

    private long totalElements;

    private boolean first;

    private long numberOfElements;

    private boolean last;

    private List<T> content;

    public PageDTO() {
    }

    public PageDTO(int number, int size, int totalPage, long totalElements, boolean first, long numberOfElements, boolean last, List<T> content) {
        this.number = number;
        this.size = size;
        this.totalPage = totalPage;
        this.totalElements = totalElements;
        this.first = first;
        this.numberOfElements = numberOfElements;
        this.last = last;
        this.content = content;
    }

    public int getNumber() {
        return number;
    }

    public int getSize() {
        return size;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public boolean isFirst() {
        return first;
    }

    public long getNumberOfElements() {
        return numberOfElements;
    }

    public boolean isLast() {
        return last;
    }

    public List<T> getContent() {
        return content;
    }
}
