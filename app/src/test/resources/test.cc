#include <iostream>

using namespace std;

int main(void) {
    int h, m;
    cin >> h >> m;
    cout << (12 - h) % 12 << " " << (60 - m) % 60 << endl;
    return 0;
}
