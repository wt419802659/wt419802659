package com.example.wtbaidumap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;
import com.baidu.mapapi.map.MKMapViewListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.PoiOverlay;
import com.baidu.mapapi.map.RouteOverlay;
import com.baidu.mapapi.search.MKAddrInfo;
import com.baidu.mapapi.search.MKBusLineResult;
import com.baidu.mapapi.search.MKDrivingRouteResult;
import com.baidu.mapapi.search.MKPoiInfo;
import com.baidu.mapapi.search.MKPoiResult;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.mapapi.search.MKShareUrlResult;
import com.baidu.mapapi.search.MKSuggestionResult;
import com.baidu.mapapi.search.MKTransitRouteResult;
import com.baidu.mapapi.search.MKWalkingRouteResult;
import com.baidu.platform.comapi.basestruct.GeoPoint;
/**
 * 
 * @author wt
 * ͼ���¼�����û���г�,�ο��ٶ�API
 *
 */
public class MainActivity extends Activity{
	private Toast mToast;
//	private Button button;
	private MKSearch mMKSearch = null;// ��ͼ������
	private BMapManager bMapManager;// ��ͼ������
	private MapView mMapView = null;// ��ͼ���ؼ�
	private MapController MapController = null;// ��ͼ���ƶ���
	private MKMapViewListener mMapListener = null;// ���ڴ����ͼ�¼��ص�

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		bMapManager = new BMapManager(this);// ��ʼ����ͼ�����࣬����setContentView֮ǰ
		bMapManager.init("wRn9vejTnZIQrhIFzDzbwtKc", new MKGeneralListener() {
			// ��ʼ����ͼ
			@Override
			public void onGetNetworkState(int arg0) {// һЩ����״̬�Ĵ�����ص�����
				// TODO Auto-generated method stub
				if (MKEvent.ERROR_NETWORK_CONNECT == arg0) {
					showToast("�����������");
				}
			}

			@Override
			public void onGetPermissionState(int arg0) {// ��Ȩ�����ʱ����õĻص�����
				// TODO Auto-generated method stub
				showToast("API KEY����, ���飡");
			}

		});
		// bMapManager.start();
		// LocationManager
		// locationManager=(LocationManager)getSystemService(this.LOCATION_SERVICE);
		// if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
		// showToast("�뿪��GPS����...");
		// }
		// Location location
		// =locationManager.getLastKnownLocation(locationManager.getBestProvider(getCriteria(),
		// true));
		setContentView(R.layout.activity_main);
//		mMKSearch = new MKSearch();
//		mMKSearch.init(bMapManager, new MySearchListener());
//		button = (Button) findViewById(R.id.button);
//		button.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				mMKSearch = new MKSearch();
//				mMKSearch.init(bMapManager, new MySearchListener());
//				// 1:POI����
//				// mMKSearch.poiSearchInbounds("kfc", arg1, arg2);//��һ�����η�Χ������
////				   mMKSearch.poiSearchInCity("�ɶ�", "KFC");//���м���
//				// mMKSearch.poiSearchNearBy("kfc", arg1, "5000");//��ĳһ��Ϊ����5000M��Χ������KFC
//				// 2.��ַ��Ϣ��ѯ
////				mMKSearch.reverseGeocode(new GeoPoint(40057031, 116307852));//���ַ����
////				mMKSearch.geocode("�ɶ�", "��������");//��ַ����  
//				//3�����߽����ѯ
////				mMKSearch.suggestionSearch("key", "city");
//				//4:�̴�����
////				mMKSearch.poiDetailShareURLSearch(new MKPoiInfo().uid);//POI�����
////				mMKSearch.poiRGCShareURLSearch(new GeoPoint((int)(39.945*1E6),(int)(116.404*1E6)), "�찲��", "��������ɽǰ��4��");//��Geo�����
//		       //5:�ݳ���·����
////				MKPlanNode start=new MKPlanNode();
////				start.pt=new GeoPoint((int) (39.915 * 1E6), (int) (116.404 * 1E6));
////				MKPlanNode end=new MKPlanNode();
////				end.pt=new GeoPoint(40057031, 116307852);
//////				mMKSearch.setDrivingPolicy(MKSearch.ECAR_TIME_FIRST);// ���üݳ�·���������ԣ�ʱ�����ȡ��������ٻ������� 
//////				mMKSearch.drivingSearch(null, start, null, end);//aro0Ϊ��ʼ��Ϣ��arg2�յ���Ϣ
////				//6.����·������,��ݳ���·��������
////				mMKSearch.walkingSearch(null, start, null, end);
//				//7.������·����
////				mMKSearch.transitSearch("�ɶ�", start, end);
//				//8.���ݹ���id��������
////				mMKSearch.busLineSearch("�ɶ�", "221");
//				//�������ܣ����ðٶȵ�ͼ
////				double mLat1 = 39.915;   
////				double mLon1 = 116.404;   
////				double mLat2 = 32.032;  
////				double mLon2 = 118.799; 
////				int lat = (int) (mLat1 *1E6);  
////				int lon = (int) (mLon1 *1E6);   
////				GeoPoint pt1=new GeoPoint(lat, lon);
////				lat = (int) (mLat2 *1E6);  
////				lon = (int) (mLon2 *1E6);
////				GeoPoint pt2=new GeoPoint(lat, lon);
////				NaviPara para=	new NaviPara();
////				para.startPoint=pt1;
////				para.startName="�츮�㳡";
////				para.endPoint=pt2;
////				para.endName="���ͳ�";
////				try{
////					BaiduMapNavigation.openBaiduMapNavi(para, MainActivity.this);
////				}catch(BaiduMapAppNotSupportNaviException  e){
////					 e.printStackTrace();  
////				}
//             //�Զ�����ӿؼ��ڵ�ͼ��,�˹����ڵ�ͼ�ϱȽ�����
////				 Button button2 = new Button(MainActivity.this);  
////				 button.setText("Hello World"); 
////				 GeoPoint pt =new GeoPoint((int) (22.547923 * 1E6),
////							(int) (114.067368 * 1E6)); 
//				 mMapView.addView(button,  new MapView.LayoutParams( MapView.LayoutParams.WRAP_CONTENT,  MapView.LayoutParams.WRAP_CONTENT, pt,  MapView.LayoutParams.BOTTOM));
//			}
//		         new MapView.LayoutParams(width, height, X, y, alignment);
//		});
		mMapView = (MapView) this.findViewById(R.id.bmapView);
		mMapView.setTraffic(true);// ʵʱ��ͨ��Ϣͼ
		mMapView.setBuiltInZoomControls(true);//��ͼ��С�Ŵ���
		// mMapView.setSatellite(true);//���ǵ�ͼ
		MapController = mMapView.getController();// ��ȡ��ͼ������
		MapController.enableClick(true);// ����Ӧ����¼�
		MapController.setZoom(12);// �������ż���
		MapController.setZoomGesturesEnabled(true);
		GeoPoint g = new GeoPoint((int) (22.547923 * 1E6),
				(int) (114.067368 * 1E6));// ���澫�Ⱥ�γ�ȵ���
		MapController.setCenter(g);// ����p�ط�Ϊ���ĵ�
		mMapView.regMapViewListener(bMapManager, new MKMapViewListener() {

			@Override
			public void onClickMapPoi(MapPoi arg0) {// �����ͼ�ϱ���ǵĵ�ص��˷���
				// TODO Auto-generated method stub
				if (arg0 != null) {
					showToast(arg0.strText);
				}
			}

			@Override
			public void onGetCurrentMap(Bitmap arg0) {// �����ù�
														// mMapView.getCurrentMap()�󣬴˻ص��ᱻ����
				// * ���ڴ˱����ͼ���洢�豸
				// TODO Auto-generated method stub

			}

			@Override
			public void onMapAnimationFinish() { // ��ͼ��ɴ������Ĳ�������:
													// animationTo()���󣬴˻ص�������
				// TODO Auto-generated method stub

			}

			@Override
			public void onMapLoadFinish() {// ��ͼ������ϻص��˽ӿڷ���
				// TODO Auto-generated method stub
				showToast("��ͼ������ϣ�");
			}

			@Override
			public void onMapMoveFinish() {// ��ͼ�ƶ����ʱ��ص��˽ӿ� ����
				// TODO Auto-generated method stub
				showToast("��ͼ�ƶ���ϣ�");
			}
		});
	}

	protected void onResume() {
		// MapView������������Activityͬ������activity����ʱ�����MapView.onPause()
		mMapView.onResume();
		if (bMapManager != null) {
			bMapManager.start();
		}
		super.onResume();
	}

	@Override
	protected void onPause() {
		// MapView������������Activityͬ������activity����ʱ�����MapView.onPause()
		mMapView.onPause();
		if (bMapManager != null) {
			bMapManager.stop();
		}
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// MapView������������Activityͬ������activity����ʱ�����MapView.destroy()
		mMapView.destroy();

		// �˳�Ӧ�õ���BMapManager��destroy()����
		if (bMapManager != null) {
			bMapManager.destroy();
			bMapManager = null;
		}

		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void showToast(String msg) {
		if (mToast == null) {
			mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(msg);
			mToast.setDuration(Toast.LENGTH_SHORT);
		}
		mToast.show();
	}

	/**
	 * 
	 * @author wt �ٶȵ�ͼ����������Ҫʵ��MKSearchListener
	 */
	public class MySearchListener implements MKSearchListener {

		@Override
		public void onGetAddrResult(MKAddrInfo res, int error) {
			// TODO Auto-generated method stub
			// ���ص�ַ��Ϣ�������------2
		  if(error!=0){
			  showToast(String.format("����ţ�%d", error));
			  return;
		  }	
		  mMapView.getController().animateTo(res.geoPt);  //��ͼ�ƶ����õ�
		  if(res.type==MKAddrInfo.MK_GEOCODE){ //������룺ͨ����ַ���������  
			  showToast(String.format("γ�ȣ�%f ���ȣ�%f", res.geoPt.getLatitudeE6()/1e6, res.geoPt.getLongitudeE6()/1e6));
		  }
		  if(res.type==MKAddrInfo.MK_REVERSEGEOCODE){////��������룺ͨ������������ϸ��ַ���ܱ�poi  
			    showToast(res.strAddr);  
		  }
		}

		@Override
		public void onGetBusDetailResult(MKBusLineResult res, int error) {
			// TODO Auto-generated method stub
			// ���ع�����������Ϣ�������----------8
			   if (error != 0 || res == null) {  
				   showToast("��Ǹ��δ�ҵ����");
	               return;  
	       }  
			   RouteOverlay routeOverlay = new RouteOverlay(MainActivity.this, mMapView);    // �˴���չʾһ��������Ϊʾ��  
		       routeOverlay.setData(res.getBusRoute());  
		       mMapView.getOverlays().clear();  
		       mMapView.getOverlays().add(routeOverlay);  
		       mMapView.refresh();  
		       mMapView.getController().animateTo(res.getBusRoute().getStart());  
		       showToast("������·����");
		}

		@Override
		public void onGetDrivingRouteResult(MKDrivingRouteResult res, int error) {
			// TODO Auto-generated method stub
			// ���ؼݳ�·���������-----5
              if(res==null){
            	  return;
              }
              RouteOverlay routeOverlay=  new RouteOverlay(MainActivity.this, mMapView);
              routeOverlay.setData(res.getPlan(0).getRoute(0));//�˴�ѡ���һ��������һ��·����Ϊ��ʾ
              mMapView.getOverlays().clear();
              mMapView.getOverlays().add(routeOverlay);
              mMapView.refresh();
//              mMapView.getController().animateTo(res.);
              showToast(res.getPlan(0).getRoute(0).toString());
			  mMapView.getController().animateTo(res.getStart().pt);// ��������λ�õ��Զ�����ʽ�ƶ�����ͼ����
																// ���Ը����ĵ�GeoPoint����ʼ������ʾ��ͼ��
		}

		@Override
		public void onGetPoiDetailSearchResult(int arg0, int arg1) {
			// TODO Auto-generated method stub
			// ������ϸpoi�������
		}

		@Override
		public void onGetPoiResult(MKPoiResult res, int type, int error) {
			// TODO Auto-generated method stub
			// ����poi�������-----------1
			if (error == MKEvent.ERROR_RESULT_NOT_FOUND) {
				showToast("��Ǹ��δ�ҵ����");
				return;
			} else if (error != 0 || res == null) {
				showToast("����������..");
				return;
			}
			// ��poi�����ʾ����ͼ��
			PoiOverlay poiOverlay = new PoiOverlay(MainActivity.this, mMapView);// �õ�poi���ض���
			poiOverlay.setData(res.getAllPoi());
			mMapView.getOverlays().clear();
			mMapView.getOverlays().add(poiOverlay);
			mMapView.refresh();
			// ��ePoiTypeΪ2��������·����4��������·��ʱ�� poi����Ϊ��
			for (MKPoiInfo info : res.getAllPoi()) {
				if (info.pt != null) {
					mMapView.getController().animateTo(info.pt);// ��������λ�õ��Զ�����ʽ�ƶ�����ͼ����
																// ���Ը����ĵ�GeoPoint����ʼ������ʾ��ͼ��
					break;
				}
			}
			// mMKSearch.destory();
		}

		@Override
		public void onGetShareUrlResult(MKShareUrlResult res, int type,
				int error) {
			// TODO Auto-generated method stub---------------4
			// �ڴ˴���̴����󷵻ؽ��.��Ϊ���ַ�����1��POI���� ��2����GEO����
			if(error!=0||res==null){
				showToast("������������");
			return;
			}
			if(type==MKEvent.MKEVENT_POIDETAILSHAREURL){
				 //����poi����̴�  
				showToast(res.url);
			}
			if(type==MKEvent.MKEVENT_POIRGCSHAREURL){
				  //���ص�ַ��Ϣ�̴�  
				showToast(res.url);
			}
			   mMKSearch.destory();
			   
		}

		@Override
		public void onGetSuggestionResult(MKSuggestionResult res, int error) {
			// TODO Auto-generated method stub
			// �����������Ϣ�������----------3
			if (error!= 0 || res == null) {  
	            showToast("��Ǹ��δ�ҵ����");
	            return;  
	        }  
				int sug=res.getSuggestionNum();//��ȡ��������
				String[] suggestion=new String[sug];
				for(int i=0;i<sug;i++){
				String city=res.getSuggestion(i).city;
				String key=res.getSuggestion(i).key;
				suggestion[i]=city+key;
				}
				ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this, R.layout.activity_main, suggestion);
		}

		@Override
		public void onGetTransitRouteResult(MKTransitRouteResult res, int error) {
			// TODO Auto-generated method stub
			// ���ع����������-----------7
			if(res==null){
				return;
			}
			  RouteOverlay routeOverlay=  new RouteOverlay(MainActivity.this, mMapView);
              routeOverlay.setData(res.getPlan(0).getRoute(0));//�˴�ѡ���һ��������һ��·����Ϊ��ʾ
              mMapView.getOverlays().clear();
              mMapView.getOverlays().add(routeOverlay);
              mMapView.refresh();
              showToast(res.getPlan(0).getRoute(0).toString());
			  mMapView.getController().animateTo(res.getStart().pt);// ��������λ�õ��Զ�����ʽ�ƶ�����ͼ����
																// ���Ը����ĵ�GeoPoint����ʼ������ʾ��ͼ��
		}

		@Override
		public void onGetWalkingRouteResult(MKWalkingRouteResult res, int error) {
			// TODO Auto-generated method stub
			// ���ز���·���������--------6
			if(res==null){
				return;
			}
			  RouteOverlay routeOverlay=  new RouteOverlay(MainActivity.this, mMapView);
              routeOverlay.setData(res.getPlan(0).getRoute(0));//�˴�ѡ���һ��������һ��·����Ϊ��ʾ
              mMapView.getOverlays().clear();
              mMapView.getOverlays().add(routeOverlay);
              mMapView.refresh();
              showToast(res.getPlan(0).getRoute(0).toString());
			  mMapView.getController().animateTo(res.getStart().pt);// ��������λ�õ��Զ�����ʽ�ƶ�����ͼ����
																// ���Ը����ĵ�GeoPoint����ʼ������ʾ��ͼ��
		}

	}
}
