package com.luxoft.library.exceptions;

import com.luxoft.library.exceptions.constants.MessageResource;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Duplicate")
@Getter
public class DuplicateDataException extends RuntimeException {

    private String entityName;

    private Object entityId;

    private String field;

    private Object value;

    private final Object[] args;

    private final MessageResource messageResource;


    public DuplicateDataException(@Nullable Object entityId,
                                  @NonNull String entityName, @NonNull String field, Object value) {
        this(MessageResource.DUPLICATE_DATA, entityName, entityId, field, value);
        this.entityName = entityName;
        this.entityId = entityId;
        this.field = field;
        this.value = value;
    }

    public DuplicateDataException(@NonNull MessageResource messageResource,
                                  @NotNull Object... messageArgs) {
        super(messageResource.name() + Arrays.toString(messageArgs));
        this.messageResource = messageResource;
        this.args = messageArgs;
    }

}
