package RFID;

public class rfidlib_AIP_ISO15693 {
	
	static{
		System.loadLibrary("jni_rfidlib_aip_iso15693");
	}
	
	
	public native static int ISO15693_GetLibVersion(char[] buf ,int nSize /* in character */)  ;
	/*
	*	iso15693 inventory parameters
	*/
	// inventory need to match the AFI value 
	public native static long ISO15693_CreateInvenParam(long hInvenParamSpecList,
																byte AntennaID/* By default set to 0,apply to all antenna */,
																byte en_afi,
																byte afi,
																byte slot_type/*0:default,1: 1slot,16:16 slot */)   ;
	/*
	* Parse iso15693 tag data 
	*/
	public native static int ISO15693_ParseTagDataReport(long hTagReport,Integer aip_id,Integer tag_id,
											  Integer ant_id,Byte dsfid,byte uid[]);



	public native static int ISO15693_Connect(long hr,int tagType,byte address_mode,
								byte[] uid,Long ht);

	public native static int ISO15693_Reset(long hr ,long ht) ;
	public native static int ISO15693_ReadSingleBlock(long hr ,long ht, byte readSecSta,int blkAddr,
							byte bufBlockDat[],int nSize,Integer bytesBlkDatRead);
	public native static int ISO15693_WriteSingleBlock(long hr ,long ht,int blkAddr,byte[] newBlkData,int bytesToWrite);
	public native static int ISO15693_LockBlock(long hr,long ht,int blkAddr);
	public native static int ISO15693_ReadMultiBlocks(long hr,long ht,byte readSecSta,int blkAddr,int numOfBlksToRead,
			Integer numOfBlksRead,byte[] bufBlocks,int nSize,Integer bytesBlkDatRead);
	public native static int ISO15693_WriteMultipleBlocks(long hr,long ht,int blkAddr,int numOfBlks, 
								byte[] newBlksData,int bytesToWrite);

	public native static int ISO15693_WriteAFI(long hr,long ht,byte afi);

	public native static int ISO15693_LockAFI(long hr,long ht);
	public native static int ISO15693_WriteDSFID(long hr,long ht,byte dsfid);
	public native static int ISO15693_LockDSFID(long hr,long ht);
	public native static int ISO15693_GetSystemInfo(long hr,long ht,byte[]uid,Byte dsfid,Byte afi,
												Integer blkSize ,Integer numOfBloks,Byte icRef) ;

	public native static int ISO15693_GetBlockSecStatus(long hr ,long ht,int blkAddr, 
											int numOfBlks,byte[] bufBlkSecs,
											int nSize/* in: size of the buffer */,
											Integer bytesSecRead /*out:number of block status byte copied*/ ) ;

	public native static int ISO15693_LockMultipleBlocks(long hr,long ht,int blkAddr,int numOfBlks);

	public native static int FUJMB89R118C_WriteEAS(long hr,long ht,byte EASFlag /* 0:disable ,1:enable */)  ;

	public native static int FUJMB89R118C_DetectEAS(long hr,long ht,byte[] buffer,int nSize/* IN:buffer size */,
			Integer BytesRead /* OUT: bytes written to buffer */) ;
	public native static int FUJMB89R118C_ReadMultiBlksUnlimited(long hr ,
														  long ht, /* Tag handle connected */
														  byte read_sec_sta ,
														  byte blk_no,/* Block number  */
														  Byte num_of_blks, /* in:Number of blocks to read ,out: blocks read */
														  byte[] buf_blks,/*  buffer for storing block data ,8 or 9 * num_of_blks */
														  Integer nSize /* in:size of the buffer ,out: bytes copied */)  ;

	public native static int NXPICODESLI_EableEAS(long hr ,long ht)  ;
	public native static int NXPICODESLI_DisableEAS(long hr,long ht) ;
	public native static int NXPICODESLI_LockEAS(long hr,long ht);

	public native static int NXPICODESLI_EASCheck(long hr,long ht,Byte EASFlag);


	public native static int NXPICODESLIX_EableEAS(long hr,long ht)  ;
	public native static int NXPICODESLIX_DisableEAS(long hr,long ht) ;
	public native static int NXPICODESLIX_LockEAS(long hr ,long ht) ;
	public native static int NXPICODESLIX_EASAlarm(long hr,long ht,byte[] EAS_data,int nSize,Integer bytesWritten);
	public native static int NXPICODESLIX_GetRandomNum(long hr,long ht,Integer random/* 16bits */) ;
	public native static int NXPICODESLIX_SetPassword(long hr,long ht,
											   byte pwdNo,/* password adress,Only one password is supported now,the address is 10H */
											   int random,int pwd/* 32bits */)  ;

	public native static int NXPICODESLIX_WritePassword(long hr ,
												 long ht,
												 byte pwdNo,
												 int pwd/* 32bits */)  ;
	public native static int NXPICODESLIX_LockPassword(long hr ,
												long ht,
												byte pwdNo) ;
	public native static int NXPICODESLIX_PasswordProtect(long hr ,
													 long ht,
													 byte bandType/* EAS=0 or AFI=1 */) ;

	public native static int NXPICODESLIX_EASCheck(long hr,long ht,Byte EASFlag) ;

	public native static int STM24LR64_UpdatePassword(long hr ,
											   long ht,
											   byte pwdnum,
											   int old_pwd,
											   int new_pwd)  ;
	public native static int STM24LR64_AuthPassword(long hr,long ht,byte pwdnum,int pwd) ;
	public native static int STM24LR64_ActivateSectorSecurity(long hr,long ht,
														byte sector_num,byte access_type,
														byte pwd_num_protect) ;


	public native static int STM24LR16E_UpdatePassword(long hr,long ht,byte pwdnum,int old_pwd,int new_pwd);

	public native static int STM24LR16E_AuthPassword(long hr,long ht,byte pwdnum,int pwd);

	public native static int STM24LR16E_ActivateSectorSecurity(long hr,long ht,byte sector_num,byte access_type,byte pwd_num_protect) ;

	public native static int STM24LR16E_ReadCFGByte(long hr,long ht,Byte cfgby ) ;

	public native static int STM24LR16E_SetCFGEH(long hr,long ht,byte EnergyHarvesting,byte EHMode) ;

	public native static int STM24LR16E_SetCFGDO(long hr,long ht,
										   byte cfg_do /* 0: RF busy mode(RF WIP/BUSY pin for RF busy) , 1: RF Write in progress( RF WIP/BUSY pin for) */
										   ) ;


	public native static int STM24LR16E_SetCtrlRegEHEN(long hr,long ht,byte enable);

	public native static int STM24LR16E_ReadCtrlReg(long hr,long ht,Byte ctrlreg) ;

	public native static int TIHFIPLUS_Write2Blocks(long hr ,
											 long ht,
											 int blkAddr,
											 byte[] newTwoBlksData /* 2 * 4 */,
											 int bytesToWrite
											 ) ;

	public native static int TIHFIPLUS_Lock2Blocks(long hr ,long ht,int blkAddr) ;
}
