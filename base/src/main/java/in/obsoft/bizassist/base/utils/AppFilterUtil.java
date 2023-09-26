package in.obsoft.bizassist.base.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class AppFilterUtil {

    public static Pageable getPageable(int pageNo, int pageSize, String sortOn, String sortDirection) {
        Pageable pageable;
        if(sortDirection.equalsIgnoreCase("ASC")) {
            pageable = PageRequest.of(pageNo, pageSize).withSort(Sort.by(Sort.Direction.ASC, sortOn));
        }
        else {
            pageable = PageRequest.of(pageNo, pageSize).withSort(Sort.by(Sort.Direction.DESC, sortOn));
        }
        return pageable;
    }

}
