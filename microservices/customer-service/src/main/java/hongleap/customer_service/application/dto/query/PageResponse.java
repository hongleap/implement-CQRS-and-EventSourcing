package hongleap.customer_service.application.dto.query;

import lombok.Builder;

@Builder
public class PageResponse {
    private int pageNumber;
    private int pageSize;
}
