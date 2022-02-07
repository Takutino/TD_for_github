#include "stdafx.h"
#include <fstream>
#include <iostream>
#include <sstream>

#include "NtKinectDll.h"

using namespace std;

int main() {
	void* kinect = getKinect();
	float preState = 0.0;
	float state = 0.0;
	while (1) {
		preState = state;
		state = getVoiceDirection(kinect);
		//switch (state) {
		//case 2: cout << "Open" << endl; break;
		//case 3: cout << "Closed" << endl; break;
		//case 4: cout << "Lasso" << endl; break;
		//default: cout << "unknown" << endl;  break;
		//}
		//cout << preState << "";
		//cout << state << endl;

		std::ofstream writing_file;
		std::string filename = "sample.txt";
		//writing_file.open(filename, std::ios::out);
		writing_file.open(filename, std::ios::app);
		if (abs(state - preState) > 0.05 )
		{
			writing_file << state << std::endl;
		}
		writing_file.close();
	}
	return 1;
}
