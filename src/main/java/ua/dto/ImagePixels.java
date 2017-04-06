package ua.dto;

import org.springframework.web.multipart.MultipartFile;

public class ImagePixels {

	/**
	 * 
	 * MultipartFile - a representation of an uploaded file received in a
	 * multipart request.
	 * 
	 * @param fileFirst
	 *            The file contents are either stored in memory or temporarily
	 *            on disk.The first picture to be compared
	 * 
	 */

	private MultipartFile fileFirst;

	/**
	 * @param fileSecond
	 *            The file contents are either stored in memory or temporarily
	 *            on disk.The second picture to be compared
	 */

	private MultipartFile fileSecond;
	
	/*
    /**********************************************************
    /* Method get() and set()
    /**********************************************************
     */

	public MultipartFile getFileFirst() {
		return fileFirst;
	}

	public void setFileFirst(MultipartFile fileFirst) {
		this.fileFirst = fileFirst;
	}

	public MultipartFile getFileSecond() {
		return fileSecond;
	}

	public void setFileSecond(MultipartFile fileSecond) {
		this.fileSecond = fileSecond;
	}

}
