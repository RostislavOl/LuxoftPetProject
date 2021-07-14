package com.luxoft.library.exceptions;

import com.luxoft.library.exceptions.constants.MessageResource;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;

/**
 * Ошибка отсутствующей сущности.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not found")
@Getter
public class DataNotFoundedExceptions extends RuntimeException {

    private String entityName;

    private String entityId;

    private final String reason = "Not found";

    private final MessageResource messageResource;

    private final Object[] messageArgs;

    public DataNotFoundedExceptions(@NonNull String entityName, @NonNull Object entityId) {
        this(MessageResource.DATA_NOT_FOUND, entityName, entityId);
        this.entityName = entityName;
        this.entityId = String.valueOf(entityId);
    }

    public DataNotFoundedExceptions(MessageResource messageResource, Object... messageArgs) {
        super(messageResource.name() + Arrays.toString(messageArgs));
        this.messageResource = messageResource;
        this.messageArgs = messageArgs;
    }

}
