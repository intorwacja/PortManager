package org.amw.portmanager.application.validation.route;

import org.amw.portmanager.domain.model.Route;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
class RouteDateValidator implements RouteValidator{
    @Override
    public Map<String, String> validate(Route route) {
        Map<String, String> errors = new HashMap<>();

        if(startDateIsAfterEndDate(route.getStartTime(), route.getEndTime())) {
            errors.put("startDate", "startDate is after endDate");
        }

        return errors;
    }

    private boolean startDateIsAfterEndDate(LocalDateTime startTime, LocalDateTime endTime) {
        return startTime.isAfter(endTime);
    }
}
