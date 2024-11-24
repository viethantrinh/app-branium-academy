package net.branium.data.model.dto.response.exam

data class ExamResponse(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val questionCount: Int = 0,
    val questionResponses: List<QuestionResponse> = emptyList()
)

/**
 * {
 *         "id": 21,
 *         "title": "Bài kiểm tra cuối chương 2",
 *         "description": "Đây là bài quiz kiểm tra kiến thức đã học của chương 2 thuộc khóa học Flutter Pro. Chương này bao gồm các câu hỏi trắc nghiệm cơ bản về ngôn ngữ lập trình Dart.",
 *         "questionCount": 10,
 *         "questionResponses": [
 *             {
 *                 "id": 1,
 *                 "title": "Dart là ngôn ngữ lập trình được phát triển bởi công ty nào?",
 *                 "answer1": "Microsoft",
 *                 "isCorrect1": false,
 *                 "answer2": "Google",
 *                 "isCorrect2": true,
 *                 "answer3": "Facebook",
 *                 "isCorrect3": false
 *             },
 *             {
 *                 "id": 2,
 *                 "title": "Kiểu dữ liệu nào sau đây được sử dụng để khai báo một biến có thể chứa giá trị số nguyên trong Dart?",
 *                 "answer1": "String",
 *                 "isCorrect1": false,
 *                 "answer2": "int",
 *                 "isCorrect2": true,
 *                 "answer3": "double",
 *                 "isCorrect3": false
 *             },
 *             {
 *                 "id": 3,
 *                 "title": "Phương thức nào sau đây được sử dụng để in dữ liệu ra màn hình trong Dart?",
 *                 "answer1": "echo()",
 *                 "isCorrect1": false,
 *                 "answer2": "print()",
 *                 "isCorrect2": true,
 *                 "answer3": "console.log()",
 *                 "isCorrect3": false
 *             },
 *             {
 *                 "id": 4,
 *                 "title": "Biến trong Dart có thể thay đổi kiểu dữ liệu không khi được khai báo bằng từ khóa nào?",
 *                 "answer1": "final",
 *                 "isCorrect1": false,
 *                 "answer2": "const",
 *                 "isCorrect2": false,
 *                 "answer3": "var",
 *                 "isCorrect3": true
 *             },
 *             {
 *                 "id": 5,
 *                 "title": "Đâu là cách khai báo một hằng số trong Dart?",
 *                 "answer1": "var constantValue = 10;",
 *                 "isCorrect1": false,
 *                 "answer2": "const constantValue = 10;",
 *                 "isCorrect2": true,
 *                 "answer3": "final constantValue = 10;",
 *                 "isCorrect3": false
 *             },
 *             {
 *                 "id": 6,
 *                 "title": "Phát biểu nào sau đây về từ khóa async là đúng?",
 *                 "answer1": "Dùng để tạo một hàm bất đồng bộ.",
 *                 "isCorrect1": true,
 *                 "answer2": "Dùng để tạo một biến bất đồng bộ.",
 *                 "isCorrect2": false,
 *                 "answer3": "Dùng để khai báo một lớp bất đồng bộ.",
 *                 "isCorrect3": false
 *             },
 *             {
 *                 "id": 7,
 *                 "title": "Đối tượng Future trong Dart dùng để xử lý gì?",
 *                 "answer1": "Xử lý giao diện người dùng.",
 *                 "isCorrect1": false,
 *                 "answer2": "Xử lý các tác vụ không đồng bộ.",
 *                 "isCorrect2": true,
 *                 "answer3": "Xử lý chuỗi ký tự.",
 *                 "isCorrect3": false
 *             },
 *             {
 *                 "id": 8,
 *                 "title": "Đâu là cách đúng để khai báo một danh sách (list) rỗng trong Dart?",
 *                 "answer1": "var myList = {};",
 *                 "isCorrect1": false,
 *                 "answer2": "var myList = [];",
 *                 "isCorrect2": true,
 *                 "answer3": "var myList = ();",
 *                 "isCorrect3": false
 *             },
 *             {
 *                 "id": 9,
 *                 "title": "Phát biểu nào về từ khóa final và const là chính xác?",
 *                 "answer1": "final là một hằng số có thể được khởi tạo lại.",
 *                 "isCorrect1": false,
 *                 "answer2": "const là hằng số chỉ được xác định tại thời gian biên dịch.",
 *                 "isCorrect2": true,
 *                 "answer3": "final và const là từ khóa có thể thay đổi giá trị sau khi khởi tạo.",
 *                 "isCorrect3": false
 *             },
 *             {
 *                 "id": 10,
 *                 "title": "Hàm main() trong Dart có vai trò gì?",
 *                 "answer1": "Là điểm bắt đầu thực thi của chương trình.",
 *                 "isCorrect1": true,
 *                 "answer2": "Được dùng để xử lý lỗi trong chương trình.",
 *                 "isCorrect2": false,
 *                 "answer3": "Được dùng để khai báo lớp.",
 *                 "isCorrect3": false
 *             }
 *         ]
 *     }
 */