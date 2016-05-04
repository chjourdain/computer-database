package com.excilys.computerdatabase.mapper;

import java.util.Map;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PagerRequestMapper {

    /**
     * Method which map the parameter send by the view into a PageRequest.
     * 
     * @param param,
     *            contains current_page, size of page, the order.
     * @return
     */
    public static PageRequest get(Map<String, String> param) {
        int page = 0;
        int size = 10;
        Sort sort = null;

        if (param.get("Page") != null && !param.get("Page").isEmpty()) {
            page = Integer.parseInt(param.get("Page"));
        }
        if (param.get("Nb") != null && !param.get("Nb").isEmpty()) {
            size = Integer.parseInt(param.get("Nb"));
        }
        if (param.get("order") != null && !param.get("order").isEmpty()) {
            sort = new Sort(Direction.ASC, "order");
        }
        return new PageRequest(page, size, sort);
    }
}