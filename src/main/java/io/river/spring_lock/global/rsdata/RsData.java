package io.river.spring_lock.global.rsdata;

import static org.springframework.http.HttpStatus.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RsData<T> {

	@NonNull
	private int resultCode;
	@NonNull
	private String msg;
	@NonNull
	private T data;

	public static RsData<Void> of(String msg) {
		return of(OK.value(), msg, null);
	}

	public static <T> RsData<T> of(T data) {
		return of(OK.value(), "성공", data);
	}

	public static <T> RsData<T> of(String msg, T data) {
		return of(OK.value(), msg, data);
	}

	public static <T> RsData<T> of(int resultCode, String msg) {
		return of(resultCode, msg, null);
	}

	public static <T> RsData<T> of(int resultCode, String msg, T data) {
		return new RsData<>(resultCode, msg, data);
	}

	@JsonIgnore
	public boolean isSuccess() {
		return getResultCode() >= 200 && getResultCode() < 400;
	}

	@JsonIgnore
	public boolean isFail() {
		return !isSuccess();
	}

	public RsData<T> newDataOf(T data) {
		return new RsData<>(resultCode, msg, data);
	}
}
