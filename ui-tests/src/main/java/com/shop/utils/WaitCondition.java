package com.shop.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.function.BiFunction;
import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public enum WaitCondition {

    visible(ExpectedConditions::visibilityOfElementLocated),
    enabled((Function<By, ExpectedCondition<?>>) ExpectedConditions::elementToBeClickable),
    invisible(ExpectedConditions::invisibilityOfElementLocated),
    frameAvailable((Function<By, ExpectedCondition<?>>) ExpectedConditions::frameToBeAvailableAndSwitchToIt),
    present(ExpectedConditions::presenceOfElementLocated);

    private final BiFunction<?, ?, ExpectedCondition<?>> type;

    <T, V> WaitCondition(final Function<T, ExpectedCondition<?>> type) {
        this((T arg1, V arg2) -> type.apply(arg1));
    }

    public <T, V, R> BiFunction<T, V, R> getType() {
        return (BiFunction<T, V, R>) type;
    }

}
