#include "stdafx.h"
#include "NtKinectDll.h"
#define USE_AUDIO
#include "NtKinect.h"

using namespace std;

NTKINECTDLL_API void* getKinect(void) {
	NtKinect* kinect = new NtKinect;
	return static_cast<void*>(kinect);
}

NTKINECTDLL_API float getVoiceDirection(void* ptr) {

	NtKinect* kinect = static_cast<NtKinect*>(ptr);
	cv::Mat beam;
	(*kinect).setRGB();
	(*kinect).setAudio();
	cv::putText((*kinect).rgbImage, "beamAngle: " + to_string((*kinect).beamAngle),
	cv::Point(50, 50), cv::FONT_HERSHEY_SIMPLEX, 1.2, cv::Scalar(0, 0, 255), 1, cv::LINE_AA);
	// rename CV_AA as cv::LINE_AA (in case of opencv3 and later)
	cv::putText((*kinect).rgbImage, "beamAngleConfidence: " + to_string((*kinect).beamAngleConfidence),
	cv::Point(50, 80), cv::FONT_HERSHEY_SIMPLEX, 1.2, cv::Scalar(0, 0, 255), 1, cv::LINE_AA);
	cv::imshow("rgb", (*kinect).rgbImage);
	(*kinect).drawAudioDirection(beam);
	cv::imshow("beam", beam);
	cv::waitKey(1);
	
	return (*kinect).beamAngle;
	


	/*NtKinect *kinect = static_cast<NtKinect*>(ptr);
	(*kinect).setRGB();
	(*kinect).setSkeleton();
	int scale = 4;
	cv::Mat img((*kinect).rgbImage);
	cv::resize(img, img, cv::Size(img.cols / scale, img.rows / scale), 0, 0);
	for (auto person : (*kinect).skeleton) {
		for (auto joint : person) {
			if (joint.TrackingState == TrackingState_NotTracked) continue;
			ColorSpacePoint cp;
			(*kinect).coordinateMapper->MapCameraPointToColorSpace(joint.Position, &cp);
			cv::rectangle(img, cv::Rect((int)cp.X / scale - 2, (int)cp.Y / scale - 2, 4, 4), cv::Scalar(0, 0, 255), 2);
		}
	}
	cv::imshow("rgb", img);
	cv::waitKey(1);
	for (int i = 0; i<(*kinect).skeleton.size(); i++) {
		Joint right = (*kinect).skeleton[i][JointType_HandRight];
		if (right.TrackingState == TrackingState_NotTracked) continue;
		auto state = (*kinect).handState(i, false);
		if (state.first == HandState_Open
			|| state.first == HandState_Closed
			|| state.first == HandState_Lasso) {
			return state.first;
		}
	}
	return HandState_Unknown;*/



}