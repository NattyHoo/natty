package com.hoo.bean;

import java.util.Collection;

/**
 * <pre>
 * <p>Title: 返回结果</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2010 - 2016 </p>
 * <p>Company: Beijing jcfy Technology Co.,Ltd. </p>
 * <p>Build: 2016年11月7日 下午4:17:17 </p>
 * </pre>
 *
 * @author Chang Yuxin
 * @version 1.0
 */
public class Result {
	public static final String SUCCESS = "1";
	public static final String FAILURE = "0";

	/** 状态 */
	private String status = "";

	/** 系统错误代码 */
	private String errorCode = "";

	/** 消息 */
	private String errorMsg = "";

	/** 结果 */
	private Object data;
	/**
	 * 后台管理使用展示信息
	 */
	private String msg;

	public Result() {
		super();
	}

	/**
	 * 返回成功的对象
	 *
	 * @return Result
	 */
	public static Result success() {
		return new Result(SUCCESS, "", "", "");
	}

	/**
	 * 返回成功对象
	 *
	 * @param msg
	 * @return
	 */
	public static Result success(String msg) {
		return new Result(SUCCESS, msg);
	}

	/**
	 * 返回成功的对象
	 *
	 * @return Result
	 */
	public static Result success(Result result) {
		result.setStatus(SUCCESS);
		return result;
	}

	/**
	 * 返回成功的对象
	 *
	 * @param data
	 * @return Result
	 */
	public static Result success(Object data) {
		return new Result(SUCCESS, "", "", data);
	}

	/**
	 * 返回成功的对象
	 *
	 * @param data
	 * @param total
	 * @return esult
	 */
	public static Result success(Object data, long total) {
		return new Result(SUCCESS, "", "", data, total);
	}

	/**
	 * 返回失败对象
	 *
	 * @param error
	 * @return Result
	 */
	public static Result failure(Error error) {
		return new Result(FAILURE, error.getErrorCode(), error.getErrorMsg(), "");
	}

	/**
	 * 处理返回对象
	 *
	 * @param flag
	 * @param successMsg
	 * @param failureMsg
	 */
	public static Result handleResult(boolean flag, String successMsg, String failureMsg) {
		return flag ? Result.success(successMsg) : Result.fail(failureMsg);
	}

	/**
	 * 处理返回对象
	 *
	 * @param flag
	 * @param successMsg
	 * @param failureMsg
	 */
	public static Result handleResult(int flag, String successMsg, String failureMsg) {
		return flag > 0 ? Result.success(successMsg) : Result.fail(failureMsg);
	}

	/**
	 * 统一处理是否操作成功与操作失败
	 *
	 * @return Result
	 */
	public static Result handleResult(boolean flag, String msg) {
		return flag ? Result.success(msg) : Result.fail(msg);
	}

	/**
	 * 返回失败对象
	 *
	 * @param errorCode
	 * @return Result
	 */
	public static Result failure(String errorCode) {
		return new Result(FAILURE, Error.code(errorCode), Error.msg(errorCode), null);
	}

	/**
	 * 返回失败对象
	 *
	 * @return Result
	 */
	public static Result failure(String msg, Error error) {
		return new Result(FAILURE, msg);
	}

	/**
	 * 返回失败对象
	 *
	 * @param errorCode
	 * @param errorMsg
	 * @return Result
	 */
	public static Result failure(String errorCode, String errorMsg) {
		return new Result(FAILURE, Error.code(errorCode), errorMsg + Error.msg(errorCode), null);
	}

	/**
	 * @param msg
	 * @return
	 */
	public static Result fail(String msg) {
		return new Result(FAILURE, msg);
	}

	/**
	 * 返回失败对象
	 *
	 * @param error
	 * @param errorMsg
	 * @return Result
	 */
	public static Result failure(Error error, String errorMsg) {
		return new Result(FAILURE, error.getErrorCode(), errorMsg + error.getErrorMsg(), "");
	}

	/**
	 * 返回失败对象
	 *
	 * @param error
	 * @param errorMsg
	 * @param limitCondition
	 *            限制条件,如最大长度
	 * @return Result
	 */
	public static Result failure(Error error, String errorMsg, String limitCondition) {
		return new Result(FAILURE, error.getErrorCode(), errorMsg + error.getErrorMsg() + limitCondition, "");
	}

	/**
	 * 生成返回结果对象,不包含总数量
	 *
	 * @param status
	 * @param data
	 */
	public Result(String status, String errorCode, String errorMsg, Object data) {
		this.status = status;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.data = data;
		this.msg = errorMsg;
	}

	/**
	 * 返回成功对象 带展示信息
	 *
	 * @param status
	 */
	public Result(String status, String msg) {
		this.status = status;
		this.msg = msg;
		this.errorMsg = msg;
	}

	/**
	 * 生成返回结果对象，包含总数量
	 *
	 * @param status
	 * @param data
	 */
	public Result(String status, String errorCode, String errorMsg, Object data, long total) {
		this.status = status;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.data = data;
		this.msg = errorMsg;
	}

	public static Result failure(Object data) {
		return new Result(FAILURE, "", "", data);
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg
	 *            the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
