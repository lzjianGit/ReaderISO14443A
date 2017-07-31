package RFID;

public class rfidlib_reader {
	/*********************************functions opened*****************************************************/
	static{
		System.loadLibrary("jni_rfidlib_reader");
	}
	//获取版本
	public native static int RDR_GetLibVersion(char[] buffer ,int nSize);
	//加载读卡器驱动程序
	public native static int RDR_LoadReaderDrivers(String path) ;
	//获取加载读卡器驱动程序计数
	public native static int RDR_GetLoadedReaderDriverCount() ;
	//获取加载读卡器驱动程序选项
	public native static int RDR_GetLoadedReaderDriverOpt(int idx ,String option ,char[] valueBuffer,Integer nSize/**out**/);
	//通过名称获取加载的读卡器驱动程序
	public native static int RDR_GetLoadedReaderDriverOptByName(String ID ,String option ,char[] valueBuffer,Integer nSize/***out**/) ;
	//枚举
	public native static int HID_Enum(String DevId)  ;
	//获取枚举项
	public native static int HID_GetEnumItem(int indx ,byte infType,char[] infBuffer,Integer nSize/**out**/);
	//COM端口枚举
	public native static int COMPort_Enum() ;
	//COM端口获取枚举项
	public native static int COMPort_GetEnumItem(int idx,char[] nameBuf,Integer nSize/*out*/) ;
	//打开
	public native static int RDR_Open(String connStr ,Long hrOut/*out*/);
	//关
	public native static int RDR_Close(long hr);
	//创建Inven参数规格表
	public native static long RDR_CreateInvenParamSpecList()  ;  
	//标签库存
	public native static int RDR_TagInventory(long hr,byte AIType,byte AntennaCount,byte[]AntennaIDs,long InvenParamSpecList);
	//获取标签数据报告计数
	public native static int RDR_GetTagDataReportCount(long hr);
	//获取标签数据报告
	public native static long RDR_GetTagDataReport(long hr , byte seek);
	//标签连接
	public native static int RDR_TagConnect(long hr,int ConnParms,Integer hTag/*out*/);
	//标签访问
	//public native static int RDR_TagAccess(long hr,int hTag,int Request,int Respond);
	//标签断开
	public native static int RDR_TagDisconnect(long hr,long hTag)  ;
	//断开所有标签
	public native static int RDR_DisconnectAllTags(long hr)  ;
	//获取读者最后返回错误
	public native static int RDR_GetReaderLastReturnError(long hr)  ;
	//设置一个天线
	public native static int RDR_SetAcessAntenna(long hr ,byte AntennaID) ;
    //开放射频发射机
	public native static int RDR_OpenRFTransmitter(long hr) ;
	//关闭射频发射器
	public native static int RDR_CloseRFTransmitter(long hr) ;
	//设置CommuImme超时
	public native static int RDR_SetCommuImmeTimeout(long hr) ;
	//重置CommuImme超时
	public native static int RDR_ResetCommuImmeTimeout(long hr);
	//获取天线接口计数
	public native static int RDR_GetAntennaInterfaceCount(long hr)  ;
	//取出计数
	public native static int RDR_GetOutputCount(long hr,Byte nCount);
	//取出名称
	public native static int RDR_GetOutputName(long hr,byte idxOut,char[] bufName ,Integer nSize);
	//创建设置操作
	public native static long RDR_CreateSetOutputOperations() ;
	//添加一个输出操作
	public native static int RDR_AddOneOutputOperation(long hOperas,byte outNo,byte outMode,int outFrequency,int outActiveDuration ,int outInactiveDuration) ;
	//设置输出
	public native static int RDR_SetOutput(long hr ,long outputOpers) ;
	//加载出厂默认值
	public native static int RDR_LoadFactoryDefault(long hr) ;
	//创建设置获取配置项列表
	public native static long RDR_CreateSetGetConfigItemList()  ;
	//系统复位
	public native static int RDR_SystemReset(long hr) ;
	//支持库存异步输出
	public native static int RDR_IsSupportInventoryAsyncOutput(long hr,Boolean suppported);
	//获取读者信息
	public native static int RDR_GetReaderInfor(long hr,byte Type ,char[] buffer,Integer nSize);
	//配置块写入
	public native static int RDR_ConfigBlockWrite(long hr,int cfgno ,byte[] cfgdata,int nSize,int mask) ;
	//配置块读取
	public native static int RDR_ConfigBlockRead(long hr,int cfgno,byte[]cfgbuff,int nSize);
	//配置块保存
	public native static int RDR_ConfigBlockSave(long hr,int cfgno) ;
	//创建RS485节点
	public native static int RDR_CreateRS485Node(long hr ,int busAddr,Integer ohrRS485Node);
	//获得支持的空中接口协议
	public native static int RDR_GetSupportedAirInterfaceProtocol(long hr ,int index,Integer AIPType);
	//检测噪声
	//public native static int RDR_DetectNoise(int hr ,byte[] noiseData,Integer nSize);
	//启用协议日志
	//public native static int RDR_EnableProtocolLog(int hr,byte methType,int msg ,int hwnd,int cb,int param) ;
	//关闭协议日志
	//public native static int RDR_DisalbeProtocolLog(int hr) ;
	//获取GPI计数
	public native static int RDR_GetGPICount(long hr,Integer oCount);
	//加载认证密钥
	public native static int RDR_LoadAuthKey(long hr ,byte storeType,byte keyType ,byte[] key,int keylen);
	//选择认证密钥
	public native static int RDR_SelectAuthKey(long hr ,byte keyType) ;
	//安全验证包装
	public native static int RDR_SafeAuthencate(long hr,int hTag,int params);
	//重置射频
	public native static int RDR_ResetRF(long hr);
	//破坏
	public native static int DNODE_Destroy(long hr);
	//设置回调函数
	public native static int RDR_SetEventHandler(long hr,byte eventType,int i,int msg ,int hwnd,RFID_EVENT_CALLBACK_NEW cb,long param);
	//重置回调函数
	public native static int RDR_ResetEventHandler(RFID_EVENT_CALLBACK_NEW cb,byte evenType) ;
}
