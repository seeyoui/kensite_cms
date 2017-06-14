package test.seeyoui.kensite.license.license;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class Zip {
	private OutputStream _$4 = null;
	private BufferedOutputStream _$3 = null;
	private ZipArchiveOutputStream _$2 = null;
	private String _$1 = null;

	public void packToolFiles(String paramString1, String paramString2) throws FileNotFoundException, IOException {
		if (StringUtils.isNotEmpty(paramString2))
			paramString2 = paramString2 + File.separator;
		packToolFiles(this._$2, paramString1, paramString2);
	}

	public void packToolFile(String paramString1, String paramString2, String paramString3) throws FileNotFoundException,
			IOException {
		if (StringUtils.isNotEmpty(paramString2))
			paramString2 = paramString2 + File.separator;
		File localFile = new File(paramString1);
		if (localFile.isFile()) {
			if ("".equals(paramString3))
				paramString3 = localFile.getName();
			this._$2.putArchiveEntry(new ZipArchiveEntry(paramString2 + paramString3));
			org.apache.commons.compress.utils.IOUtils.copy(new FileInputStream(localFile.getAbsolutePath()), this._$2);
			this._$2.closeArchiveEntry();
			return;
		}
	}

	public void packBytes2Zip(byte[] paramArrayOfByte, String paramString1, String paramString2) throws FileNotFoundException,
			IOException {
		this._$2.putArchiveEntry(new ZipArchiveEntry(paramString1 + paramString2));
		org.apache.commons.compress.utils.IOUtils.copy(new ByteArrayInputStream(paramArrayOfByte), this._$2);
		this._$2.closeArchiveEntry();
	}

	public void packToolFiles(ZipArchiveOutputStream paramZipArchiveOutputStream, String paramString1, String paramString2)
			throws FileNotFoundException, IOException {
		File localFile = new File(paramString1);
		if (localFile.isFile()) {
			paramZipArchiveOutputStream.putArchiveEntry(new ZipArchiveEntry(paramString2 + localFile.getName()));
			org.apache.commons.compress.utils.IOUtils.copy(new FileInputStream(localFile.getAbsolutePath()),
					paramZipArchiveOutputStream);
			paramZipArchiveOutputStream.closeArchiveEntry();
			return;
		}
		File[] arrayOfFile = localFile.listFiles();
		if ((arrayOfFile == null) || (arrayOfFile.length < 1))
			return;
		for (int i = 0; i < arrayOfFile.length; i++)
			if (arrayOfFile[i].isDirectory()) {
				packToolFiles(paramZipArchiveOutputStream, arrayOfFile[i].getAbsolutePath(), paramString2
						+ arrayOfFile[i].getName() + File.separator);
			} else {
				paramZipArchiveOutputStream.putArchiveEntry(new ZipArchiveEntry(paramString2 + arrayOfFile[i].getName()));
				org.apache.commons.compress.utils.IOUtils.copy(new FileInputStream(arrayOfFile[i].getAbsolutePath()),
						paramZipArchiveOutputStream);
				paramZipArchiveOutputStream.closeArchiveEntry();
			}
	}

	public static byte[] getZipSomeByte(String paramString1, String paramString2) throws IOException {
		File localFile = new File(paramString1);
		byte[] arrayOfByte = null;
		if (localFile.exists()) {
			ZipFile localZipFile = new ZipFile(localFile, "UTF-8");
			Enumeration localEnumeration = localZipFile.getEntries();
			while (localEnumeration.hasMoreElements()) {
				ZipArchiveEntry localZipArchiveEntry = (ZipArchiveEntry) localEnumeration.nextElement();
				if (paramString2.equals(localZipArchiveEntry.getName())) {
					arrayOfByte = org.apache.commons.io.IOUtils.toByteArray(localZipFile.getInputStream(localZipArchiveEntry));
					break;
				}
			}
			localZipFile.close();
		} else {
			throw new IOException("指定的解压文件不存在：\t" + paramString1);
		}
		return arrayOfByte;
	}

	public static void unZipToFolder(String paramString1, String paramString2) throws IOException {
		File localFile = new File(paramString1);
		if (localFile.exists()) {
			paramString2 = paramString2 + File.separator;
			FileUtils.forceMkdir(new File(paramString2));
			ZipFile localZipFile = new ZipFile(localFile, "UTF-8");
			Enumeration localEnumeration = localZipFile.getEntries();
			while (localEnumeration.hasMoreElements()) {
				ZipArchiveEntry localZipArchiveEntry = (ZipArchiveEntry) localEnumeration.nextElement();
				if (localZipArchiveEntry.isDirectory())
					FileUtils.forceMkdir(new File(paramString2 + localZipArchiveEntry.getName() + File.separator));
				else
					org.apache.commons.compress.utils.IOUtils.copy(localZipFile.getInputStream(localZipArchiveEntry), FileUtils
							.openOutputStream(new File(paramString2 + localZipArchiveEntry.getName())));
			}
		} else {
			throw new IOException("指定的解压文件不存在：\t" + paramString1);
		}
	}

	public Zip(String paramString) {
		this._$1 = paramString;
	}

	public void createZipOut() throws FileNotFoundException, IOException {
		File localFile = new File(this._$1);
		this._$4 = new FileOutputStream(localFile);
		this._$3 = new BufferedOutputStream(this._$4);
		this._$2 = new ZipArchiveOutputStream(this._$3);
		this._$2.setEncoding("UTF-8");
	}

	public void closeZipOut() throws Exception {
		this._$2.flush();
		this._$2.close();
		this._$3.flush();
		this._$3.close();
		this._$4.flush();
		this._$4.close();
	}
}