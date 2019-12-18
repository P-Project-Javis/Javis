# Javis
> :robot: 사용자의 목소리에 따라 권한 제한이 가능한 IoT 컨트롤러 솔루션.

본 프로젝트는 가천대학교 P-Project 수업에서 진행한 팀 프로젝트입니다.

* 팀장: [천재웅](https://github.com/entimer)
* 팀원: [배민수](https://github.com/Minsu-Bae), [최지민](https://github.com/jjimin327), [임지수](https://github.com/ohyeah2580)

### 개발기간
2019.11.25 ~ 2019.12.18

### 배경
현재의 음성인식 기기들에는 문제점이 있습니다. 사람이 누구든지 상관없이 명령을 수행한다는 것 입니다. 실제로 아이가 말한 명령으로 인해 물건을 주문하는 등 문제가 발생하고 있습니다. 우리 팀은 이 점에 주목해 해결방안을 제시하는 프로젝트를 진행했습니다.

P-Project가 4주라는 짧은 기간이라는 점과 팀의 기술력 부족 때문에 실제 음성인식 기기를 만들지는 못하고 안드로이드 앱을 통해 간접적으로 구현했습니다.

* 사용 언어: Kotlin(UI), Java(그 외)
* 음성처리 및 인공지능 기술: 마음AI(마인즈랩의 AI)의 TTS, STT, 화자인증 AI API.

### 스크린샷
* 메인 화면

![](https://github.com/P-Project-Javis/Javis/blob/master/screenshots/screenshot_main.jpg)

메인 화면은 명령 듣기, 처리, 출력 및 다른 화면으로 이동하는 메뉴의 역할을 수행합니다.

* 새 사용자 등록 화면

![](https://github.com/P-Project-Javis/Javis/blob/master/screenshots/screenshot_new_user1.jpg)

![](https://github.com/P-Project-Javis/Javis/blob/master/screenshots/screenshot_new_user2.jpg)

먼저 사용자의 이름을 받은 뒤, 20초 이내의 목소리를 녹음해 Maum.ai의 서버 데이터베이스에 저장합니다. 또한 권한 비교를 위해 스마트폰 내부 DB에도 저장합니다.

* 권한 설정 화면

![](https://github.com/P-Project-Javis/Javis/blob/master/screenshots/screenshot_authority_setting.jpg)

등록된 모든 사용자는 권한 설정이 가능합니다. 권한을 켜고 끄면 즉시 DB에 반영되며, 권한이 없는 명령은 수행하지 않습니다. 새 사용자 등록시 기본 권한은 on(TV), on(조명), off(가스레인지), off(온라인 주문)입니다.

* 명령 수행 시

![](https://github.com/P-Project-Javis/Javis/blob/master/screenshots/screenshot_success.jpg)

등록된 사용자이며, 권한이 존재하는 경우 명령을 수행합니다.

* 권한이 없을 시

![](https://github.com/P-Project-Javis/Javis/blob/master/screenshots/screenshot_fail.jpg)

등록된 사용자이지만, 권한이 없는 경우 명령을 거부합니다.

* 등록된 사용자가 아닐 시

![](https://github.com/P-Project-Javis/Javis/blob/master/screenshots/screenshot_no_match.jpg)

목소리가 등록된 사용자가 아닐 경우 명령을 거부합니다.

### 오픈소스 라이브러리
* [Gson](https://github.com/google/gson) by Google Licensed under Apache 2.0.
* [Material Components](https://github.com/material-components/material-components-android) by Google Licensed under Apache 2.0.
* [Lombok](https://github.com/rzwitserloot/lombok) by Project Lombok Licensed under MIT.
* [Om Recorder](https://github.com/kailash09dabhi/OmRecorder) by kailash09dabhi Licensed under Apache 2.0.
* [Retrofit2](https://github.com/square/retrofit) by Square Licensed under Apache 2.0.

### License
```
Copyright 2019 P-Project Team Javis

Licensed under the Apache License, Version 2.0 (the "License");  
you may not use this file except in compliance with the License.  
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software  
distributed under the License is distributed on an "AS IS" BASIS,  
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  
See the License for the specific language governing permissions and  
limitations under the License.
```