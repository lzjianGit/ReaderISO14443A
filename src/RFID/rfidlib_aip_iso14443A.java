package RFID;

public class rfidlib_aip_iso14443A {
	static{
		System.loadLibrary("jni_rfidlib_aip_iso14443A");
	}
	public native static int ISO14443A_GetLibVersion(char[] buf ,int nSize);
	public native static int ISO14443A_ParseTagDataReport(long hTagReport,Integer aip_id,Integer tag_id,Integer ant_id,byte[] uid,Byte uidlen);  
	public native static long ISO14443A_CreateInvenParam(long hInvenParamSpecList,byte AntennaID);

	//public native static int ULTRALIGHT_GetLibVersion(char[] buf ,int nSize) ;
	public native static int ULTRALIGHT_Connect(long hr,byte[]uid/* 7 bytes */,Long ht);
	//public native static int ULTRALIGHT_Read(int hr,int ht,byte pageNo ,byte[] pagebuf,/* four page buffer */Integer nSize);
	//public native static int ULTRALIGHT_Write(int hr ,int ht,byte pageNo,byte[] pagedata);
	public native static int ULTRALIGHT_LockPage(long hr ,long ht,byte pageType); 
	public native static int ULTRALIGHT_LockBlock(long hr ,long ht,byte blockType) ;

	public native static int ULTRALIGHT_ReadMultiplePages(long hr,long ht,byte pageStart,byte pageNum,byte[] databuf,Integer nSize);
	public native static int ULTRALIGHT_WriteMultiplePages(long hr,long ht,byte pageStart,byte pageNum ,byte[] databuf,int bytesToWrite);
	public native static int ULTRALIGHT_Authenticate(long hr,long ht, byte[]key /* 16bytes */);
	public native static int ULTRALIGHT_UpdatePassword(long hr,long ht,byte[]key);
	public native static int ULTRALIGHT_UpdateAuthConfig(long hr,long ht,byte auth0,byte auth1) ;
	//public native static int ULTRALIGHT_AuthenSafe(int hr,int ht);


	public native static int MFCL_Connect(long hr,byte tagType,byte[]uid/* 4 bytes */,Long ht);
	public native static int MFCL_ReadBlock(long hr,long ht,byte blkAddr,byte  blkData[],int nSize);
	public native static int MFCL_WriteBlock(long hr,long ht,byte blkAddr,byte[] blkData/* 16 bytes */);
	public native static int MFCL_Authenticate(long hr,long ht,byte blkAddr ,byte keyType,byte[]key) ;
	//public native static int MFCL_AuthenSafe(int hr,int ht,byte blockAddr,byte AorB) ;
	public native static int MFCL_Increment(long hr,long ht,byte blkAddr,int val);
	public native static int MFCL_Decrement(long hr,long ht,byte blkAddr,int val);

	public native static int MFCL_Restore(long hr,long ht,byte blkAddr);

	public native static int MFCL_Transfer(long hr,long ht,byte blkAddr) ;

	public native static int MFCL_FormatValueBlock(long hr,long ht,byte blkAddr,int initValue) ;
	/*public native static int MFCL_CreateAccessCondition(byte blk0AccType,byte blk1AccType,byte blk2AccType,byte trailerAccType,byte[] formattedAccCondi);
	BYTE RFIDLIB_API MFCL_Sector2Block(BYTE sector) ; 
	BYTE RFIDLIB_API MFCL_Block2Sector(BYTE block) ;
	BOOLEAN RFIDLIB_API MFCL_IsTailerBlock(BYTE blkAddr) ;
	err_t RFIDLIB_API MFCL_ParseAccessCondi(BYTE formattedAccCondi[] ,BYTE *blk0AccType,BYTE *blk1AccType,BYTE *blk2AccType,BYTE *trailerAccType);*/
}
