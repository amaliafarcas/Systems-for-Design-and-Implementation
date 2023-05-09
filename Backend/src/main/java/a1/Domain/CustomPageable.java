package a1.Domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class CustomPageable implements Pageable {
    private final int pageNumber;
    private final int pageSize;
    private final long offset;

    public CustomPageable(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.offset = (long) pageNumber * pageSize;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return Sort.unsorted();
    }

    @Override
    public Pageable next() {
        return new CustomPageable(pageNumber + 1, pageSize);
    }

    @Override
    public Pageable previousOrFirst() {
        return pageNumber > 0 ? new CustomPageable(pageNumber - 1, pageSize) : first();
    }

    @Override
    public Pageable first() {
        return new CustomPageable(0, pageSize);
    }

    @Override
    public boolean hasPrevious() {
        return pageNumber > 0;
    }

    @Override
    public boolean isPaged() {
        return true;
    }

    @Override
    public boolean isUnpaged() {
        return false;
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return new CustomPageable(pageNumber, pageSize);
    }

    @Override
    public Sort getSortOr(Sort sort) {
        return Sort.unsorted();
    }

    @Override
    public Optional<Pageable> toOptional() {
        return Optional.of(this);
    }
}