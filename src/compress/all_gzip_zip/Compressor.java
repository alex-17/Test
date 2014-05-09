package compress.all_gzip_zip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.InflaterInputStream;

import compress.CompressionMode;


/**
 * 压缩工具
 * 提供Gzip、Zip两种压缩格式
 *
 */
public class Compressor {

	private CompressionMode compressMode = CompressionMode.GZIP;
	
	private static Compressor compressor = null;
	
	private static int COMPRESS_RATIO = 8;

	private Compressor(){
		
	}
	
	public static Compressor getInstance(){
		if(compressor == null){
			synchronized(Compressor.class){
				if(compressor == null){
					compressor = new Compressor();
				}
			}
		}
		return compressor;
	}

    /**
     * 压缩数据
     * 默认采用Gzip算法
     * @param in
     * @return
     */
	public final byte[] compress(byte[] in) {
		return compress(this.compressMode,in);
	}

	public final byte[] compress(CompressionMode compressionMode,byte[] in) {
		switch (compressionMode) {
		case GZIP:
			return gzipCompress(in);
		case ZIP:
			return zipCompress(in);
	    case LZMA_7ZIP:
                try {
                    return lzmaCompress(in);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
		default:
			return gzipCompress(in);
		}
	}

    /**
     * 解压缩数�?     * @param in
     * @return
     */
	public final byte[] decompress(byte[] in) {
		return decompress(this.compressMode,in);
	}
	
	public final byte[] decompress(CompressionMode compressionMode,byte[] in) {
		switch (compressionMode) {
		case GZIP:
			return gzipDecompress(in);
		case ZIP:
			return zipDecompress(in);
		default:
			return gzipDecompress(in);
		}
	}
	
	private byte[] zipDecompress(byte[] in) {
		
		if (in == null) {
			throw new NullPointerException("Can't compress null");
		}
		
		int size = in.length * COMPRESS_RATIO;
		ByteArrayInputStream bais = new ByteArrayInputStream(in);
		InflaterInputStream is = new InflaterInputStream(bais);
		ByteArrayOutputStream baos = new ByteArrayOutputStream(size);
		try {
			byte[] uncompressMessage = new byte[size];
			while (true) {
				int len = is.read(uncompressMessage);
				if (len <= 0) {
					break;
				}
				baos.write(uncompressMessage, 0, len);
			}
			baos.flush();
			return baos.toByteArray();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(is != null){
				    is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(bais != null){
				    bais.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(baos != null){
				    baos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return baos == null ? null : baos.toByteArray();
	}

	private byte[] gzipDecompress(byte[] in) {
		if (in == null) {
			throw new NullPointerException("Can't compress null");
		}
		
		ByteArrayOutputStream bos = null;
		if (in != null) {
			ByteArrayInputStream bis = new ByteArrayInputStream(in);
			bos = new ByteArrayOutputStream();
			GZIPInputStream gis = null;
			try {
				gis = new GZIPInputStream(bis);

				byte[] buf = new byte[16 * 1024];
				int r = -1;
				while ((r = gis.read(buf)) > 0) {
					bos.write(buf, 0, r);
				}
			} catch (IOException e) {
				e.printStackTrace();
				bos = null;
			} finally {
				if (gis != null) {
					try {
						gis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return bos == null ? null : bos.toByteArray();
	}
	
	private byte[] gzipCompress(byte[] in) {
		if (in == null) {
			throw new NullPointerException("Can't compress null");
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		GZIPOutputStream gz = null;
		try {
			gz = new GZIPOutputStream(bos);
			gz.write(in);
		} catch (IOException e) {
			throw new RuntimeException("IO exception compressing data", e);
		} finally {
			if (gz != null) {
				try {
					gz.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return bos.toByteArray();
	}
	
	private byte[] zipCompress(byte[] in) {
		if (in == null) {
			throw new NullPointerException("Can't compress null");
		}
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream(in.length);
		DeflaterOutputStream os = new DeflaterOutputStream(baos);
		try {
			os.write(in);
			os.finish();
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			throw new RuntimeException("IO exception compressing data", e);
		} finally {
			try {
				baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return baos.toByteArray();
	}
	
	private byte[] lzmaCompress(byte[] in) throws IOException {
        if (in == null) {
            throw new NullPointerException("Can't compress null");
        }
        
        int inputLength = in.length;
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream(inputLength);
        java.io.BufferedOutputStream outStream = new java.io.BufferedOutputStream(baos);
        
        byte[] out = new byte[inputLength];
        
        ByteArrayInputStream bainput = new ByteArrayInputStream(out);
        
        boolean eos = false;
        
        compress.lzma.Compression.LZMA.Encoder encoder = new compress.lzma.Compression.LZMA.Encoder();
        encoder.SetEndMarkerMode(eos);
        encoder.WriteCoderProperties(outStream);
        for (int i = 0; i < 8; i++)
            outStream.write((int)(inputLength >>> (8 * i)) & 0xFF);
        
        encoder.Code(bainput, outStream, -1, -1, null);
        
        return null;
    }
	
}
