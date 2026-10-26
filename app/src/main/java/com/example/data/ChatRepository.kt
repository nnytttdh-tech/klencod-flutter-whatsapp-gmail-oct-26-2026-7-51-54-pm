package com.example.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

object ChatRepository {

    private val scope = CoroutineScope(Dispatchers.Main)

    private val initialUsers = listOf(
        User(
            id = "ai_bot",
            name = "ALHWARY AI ✦",
            email = "ai@alhwary.app",
            isOnline = true,
            lastMessage = "مرحباً بك! أنا مساعد الحواري الذكي، كيف أساعدك اليوم؟",
            unreadCount = 1,
            bio = "المساعد الذكي لتطبيق الحواري • للترجمة والإجابة السريعة",
            isAiAssistant = true
        ),
        User(
            id = "1",
            name = "أحمد محمد",
            email = "ahmed@gmail.com",
            isOnline = true,
            lastMessage = "مرحبا، كيف حالك؟ هل جربت التحديث الجديد؟",
            unreadCount = 2,
            bio = "لا إله إلا الله محمد رسول الله"
        ),
        User(
            id = "2",
            name = "Sara Ali",
            email = "sara.ali@gmail.com",
            isOnline = false,
            lastSeen = "10:30 AM",
            lastMessage = "See you tomorrow!",
            unreadCount = 0,
            bio = "Work hard, stay humble ✨"
        ),
        User(
            id = "3",
            name = "Carlos Rivera",
            email = "carlos@gmail.com",
            isOnline = true,
            lastMessage = "¿Cómo estás? Me encanta la interfaz.",
            unreadCount = 1,
            bio = "¡Pura vida! 🌴"
        ),
        User(
            id = "4",
            name = "Marie Dubois",
            email = "marie@gmail.com",
            isOnline = false,
            lastSeen = "Yesterday",
            lastMessage = "À bientôt !",
            unreadCount = 0,
            bio = "La vie est belle ☕"
        ),
        User(
            id = "5",
            name = "Yuki Tanaka",
            email = "yuki@gmail.com",
            isOnline = true,
            lastMessage = "こんにちは！お元気ですか？",
            unreadCount = 3,
            bio = "Tokyo, Japan 🌸"
        ),
        User(
            id = "6",
            name = "Hans Müller",
            email = "hans@gmail.com",
            isOnline = false,
            lastSeen = "2 hours ago",
            lastMessage = "Guten Tag!",
            unreadCount = 0,
            bio = "Engineering & Tech ⚡"
        ),
        User(
            id = "7",
            name = "Priya Sharma",
            email = "priya@gmail.com",
            isOnline = true,
            lastMessage = "नमस्ते, कैसे हो?",
            unreadCount = 0,
            bio = "Spreading positive vibes ☀️"
        ),
        User(
            id = "8",
            name = "Li Wei",
            email = "liwei@gmail.com",
            isOnline = false,
            lastSeen = "Monday",
            lastMessage = "你好！很高兴认识你。",
            unreadCount = 5,
            bio = "Always learning 📚"
        )
    )

    private val _users = MutableStateFlow(initialUsers)
    val users: StateFlow<List<User>> = _users.asStateFlow()

    private val _messages = MutableStateFlow<Map<String, List<Message>>>(emptyMap())
    val messages: StateFlow<Map<String, List<Message>>> = _messages.asStateFlow()

    private val _typingUsers = MutableStateFlow<Set<String>>(emptySet())
    val typingUsers: StateFlow<Set<String>> = _typingUsers.asStateFlow()

    private val _statuses = MutableStateFlow(
        listOf(
            StatusUpdate(
                id = "s1",
                userId = "1",
                userName = "أحمد محمد",
                timestamp = "منذ 20 دقيقة",
                statusText = "يوم جميل ومشرق مع تطبيق الحواري الرائع! ☕🌿",
                gradientStart = 0xFF008069,
                gradientEnd = 0xFF128C7E
            ),
            StatusUpdate(
                id = "s2",
                userId = "2",
                userName = "Sara Ali",
                timestamp = "45 mins ago",
                statusText = "Heading to London for the tech conference! ✈️💼",
                gradientStart = 0xFF6A11CB,
                gradientEnd = 0xFF2575FC
            ),
            StatusUpdate(
                id = "s3",
                userId = "3",
                userName = "Carlos Rivera",
                timestamp = "Today, 8:15 AM",
                statusText = "Disfrutando de una mañana increíble en la playa 🏖️",
                gradientStart = 0xFFFF512F,
                gradientEnd = 0xFFF09819
            ),
            StatusUpdate(
                id = "s4",
                userId = "5",
                userName = "Yuki Tanaka",
                timestamp = "Today, 6:00 AM",
                statusText = "桜がとても綺麗に咲いています 🌸 (Cherry blossoms in Tokyo)",
                gradientStart = 0xFFFF758C,
                gradientEnd = 0xFFFF7EB3
            )
        )
    )
    val statuses: StateFlow<List<StatusUpdate>> = _statuses.asStateFlow()

    private val _calls = MutableStateFlow(
        listOf(
            CallRecord(
                id = "c1",
                userId = "1",
                userName = "أحمد محمد",
                userEmail = "ahmed@gmail.com",
                type = CallType.VOICE,
                direction = CallDirection.INCOMING,
                timestamp = "اليوم، 10:14 ص",
                duration = "04:32"
            ),
            CallRecord(
                id = "c2",
                userId = "2",
                userName = "Sara Ali",
                userEmail = "sara.ali@gmail.com",
                type = CallType.VIDEO,
                direction = CallDirection.OUTGOING,
                timestamp = "Yesterday, 4:20 PM",
                duration = "12:15"
            ),
            CallRecord(
                id = "c3",
                userId = "3",
                userName = "Carlos Rivera",
                userEmail = "carlos@gmail.com",
                type = CallType.VOICE,
                direction = CallDirection.MISSED,
                timestamp = "Yesterday, 11:05 AM",
                duration = null
            ),
            CallRecord(
                id = "c4",
                userId = "5",
                userName = "Yuki Tanaka",
                userEmail = "yuki@gmail.com",
                type = CallType.VIDEO,
                direction = CallDirection.INCOMING,
                timestamp = "23 Oct, 9:00 PM",
                duration = "08:44"
            )
        )
    )
    val calls: StateFlow<List<CallRecord>> = _calls.asStateFlow()

    // Active call simulation state
    data class ActiveCallState(
        val isActive: Boolean = false,
        val user: User? = null,
        val isVideo: Boolean = false,
        val isMuted: Boolean = false,
        val isSpeaker: Boolean = false,
        val callDurationSeconds: Int = 0
    )

    private val _activeCall = MutableStateFlow(ActiveCallState())
    val activeCall: StateFlow<ActiveCallState> = _activeCall.asStateFlow()

    init {
        // Initialize default conversations
        val defaultMap = mutableMapOf<String, List<Message>>()

        // Conversation with Ahmed
        defaultMap["1"] = listOf(
            Message("m1_1", "1", "me", "السلام عليكم ورحمة الله وبركاته، كيف حالك أخي؟", "10:10 AM", false),
            Message("m1_2", "me", "1", "وعليكم السلام ورحمة الله! الحمد لله بخير ونعمة، كيف حالك أنت؟", "10:12 AM", true),
            Message("m1_3", "1", "me", "الحمد لله، كنت أريد أن أسألك هل جربت تطبيق الحواري الجديد؟ تصميمه وسرعته ممتازة جداً!", "10:14 AM", false),
            Message("m1_4", "me", "1", "نعم تطبيق الحواري يدعم لغات متعددة والذكاء الاصطناعي وواجهة فائقة السرعة!", "10:15 AM", true),
            Message("m1_5", "1", "me", "مرحبا، كيف حالك؟ هل جربت التحديث الجديد؟", "10:20 AM", false)
        )

        // Conversation with Sara
        defaultMap["2"] = listOf(
            Message("m2_1", "2", "me", "Hi there! Just checking if we're still on for the project review today?", "Yesterday", false),
            Message("m2_2", "me", "2", "Yes absolutely, I've prepared all the metrics and screens.", "Yesterday", true),
            Message("m2_3", "2", "me", "See you tomorrow!", "Yesterday", false)
        )

        // Conversation with Carlos
        defaultMap["3"] = listOf(
            Message("m3_1", "3", "me", "¡Hola amigo! ¿Qué tal todo?", "9:30 AM", false),
            Message("m3_2", "me", "3", "¡Hola Carlos! Todo muy bien por aquí. ¿Y tú qué tal?", "9:35 AM", true),
            Message("m3_3", "3", "me", "¿Cómo estás? Me encanta la interfaz.", "9:40 AM", false)
        )

        // Conversation with Yuki
        defaultMap["5"] = listOf(
            Message("m5_1", "5", "me", "こんにちは！プロジェクトの調子はどうですか？", "8:10 AM", false),
            Message("m5_2", "me", "5", "順調に進んでいます！ありがとう。", "8:15 AM", true),
            Message("m5_3", "5", "me", "こんにちは！お元気ですか？", "8:20 AM", false)
        )

        // Conversation with AI Bot
        defaultMap["ai_bot"] = listOf(
            Message("m_ai_1", "ai_bot", "me", "أهلاً بك في الحواري! أنا مساعدك الذكي ✦\nيمكنني مساعدتك في الترجمة بين اللغات، صياغة الرسائل، وتلخيص المحادثات. كيف يمكنني خدمتك اليوم؟", "الآن", false)
        )

        _messages.value = defaultMap
    }

    private fun getCurrentTime(): String {
        val sdf = SimpleDateFormat("h:mm a", Locale.getDefault())
        return sdf.format(Date())
    }

    fun markAsRead(userId: String) {
        _users.update { list ->
            list.map { if (it.id == userId) it.copy(unreadCount = 0) else it }
        }
    }

    fun sendMessage(userId: String, text: String) {
        if (text.isBlank()) return
        val time = getCurrentTime()
        val newMsg = Message(
            id = UUID.randomUUID().toString(),
            senderId = "me",
            receiverId = userId,
            text = text.trim(),
            timestamp = time,
            isFromMe = true,
            status = MessageStatus.READ
        )

        // Add to messages
        _messages.update { currentMap ->
            val list = currentMap[userId].orEmpty() + newMsg
            currentMap + (userId to list)
        }

        // Update user lastMessage
        _users.update { list ->
            list.map { if (it.id == userId) it.copy(lastMessage = text.trim(), lastSeen = time) else it }
        }

        // Simulate contact reply
        triggerAutoReply(userId, text.trim())
    }

    private fun triggerAutoReply(userId: String, incomingText: String) {
        val user = _users.value.find { it.id == userId } ?: return

        scope.launch {
            delay(800)
            _typingUsers.update { it + userId }
            delay(1500)
            _typingUsers.update { it - userId }

            val replyText = generateReply(user, incomingText)
            val replyMsg = Message(
                id = UUID.randomUUID().toString(),
                senderId = userId,
                receiverId = "me",
                text = replyText,
                timestamp = getCurrentTime(),
                isFromMe = false,
                status = MessageStatus.READ
            )

            _messages.update { currentMap ->
                val list = currentMap[userId].orEmpty() + replyMsg
                currentMap + (userId to list)
            }

            _users.update { list ->
                list.map {
                    if (it.id == userId) it.copy(lastMessage = replyText) else it
                }
            }
        }
    }

    private fun generateReply(user: User, prompt: String): String {
        if (user.isAiAssistant) {
            val lower = prompt.lowercase()
            return when {
                "مرحبا" in prompt || "السلام" in prompt || "hello" in lower || "hi" in lower ->
                    "أهلاً وسهلاً بك! تطبيق الحواري يدعم أكثر من 15 لغة عالمية، وميزات ذكية لتسهيل التواصل الفوري. هل تود تجربة ميزة معينة؟"
                "ترجم" in prompt || "translate" in lower ->
                    "بالتأكيد! تطبيق الحواري يوفر ترجمة ذكية فورية لجميع المحادثات بنقرة واحدة."
                "الحواري" in prompt || "alhwary" in lower ->
                    "الحواري هو تطبيق مراسلة عربي وعالمي متكامل، يتميز بالسرعة والجمال وسهولة الاستخدام وأمان المحادثات."
                else ->
                    "شكراً لرسالتك! بصفتي مساعد الحواري الذكي، أنا جاهز دوماً لمساعدتك في التلخيص، الترجمة، أو الرد على استفساراتك."
            }
        }

        return when (user.id) {
            "1" -> { // Ahmed (Arabic)
                if ("كيف" in prompt || "حالك" in prompt) {
                    "أنا بخير والحمد لله! شكراً لسؤالك يا أخي. ممتن جداً لتواصلك معنا عبر الحواري."
                } else if ("موعد" in prompt || "لقاء" in prompt || "متى" in prompt) {
                    "إن شاء الله نلتقي غداً بعد صلاة العصر، ما رأيك؟"
                } else {
                    "تمام يا غالي، وصلت رسالتك وبإذن الله نتواصل قريباً! تطبيق الحواري ممتاز جداً."
                }
            }
            "2" -> "Thanks for reaching out! Looking forward to our next collaboration. Talk soon!"
            "3" -> "¡Excelente mensaje! Me alegra mucho estar en contacto por aquí. Saludos cordiales."
            "4" -> "Merci beaucoup pour ton message ! C'est toujours un plaisir de discuter."
            "5" -> "メッセージありがとうございます！今日も良い一日をお過ごしください。"
            "6" -> "Vielen Dank für deine Nachricht! Ich melde mich später noch einmal."
            "7" -> "बहुत-बहुत धन्यवाद! आपसे बात करके बहुत अच्छा लगा।"
            "8" -> "收到你的消息了，非常感谢！我们保持联系。"
            else -> "تم استلام رسالتك بنجاح، شكراً لتواصلك عبر تطبيق الحواري!"
        }
    }

    fun translateMessage(messageId: String, userId: String) {
        _messages.update { currentMap ->
            val list = currentMap[userId].orEmpty().map { msg ->
                if (msg.id == messageId) {
                    val translated = when {
                        "こんにちは" in msg.text -> "مرحباً! كيف حالك؟ (ترجمة من اليابانية)"
                        "See you" in msg.text -> "أراك غداً! (ترجمة من الإنجليزية)"
                        "Cómo estás" in msg.text -> "كيف حالك؟ أنا معجب بالواجهة. (ترجمة من الإسبانية)"
                        "À bientôt" in msg.text -> "إلى اللقاء قريباً! (ترجمة من الفرنسية)"
                        "Guten Tag" in msg.text -> "طاب يومك! (ترجمة من الألمانية)"
                        "नमस्ते" in msg.text -> "مرحباً، كيف حالك؟ (ترجمة من الهندية)"
                        "你好" in msg.text -> "مرحباً! سعيد بالتعرف عليك. (ترجمة من الصينية)"
                        else -> "الترجمة الذكية: " + msg.text
                    }
                    msg.copy(translatedText = translated)
                } else msg
            }
            currentMap + (userId to list)
        }
    }

    fun addNewContact(name: String, email: String): User {
        val newId = UUID.randomUUID().toString()
        val newUser = User(
            id = newId,
            name = name.ifBlank { "مستخدم جديد" },
            email = email.ifBlank { "user@gmail.com" },
            isOnline = true,
            lastMessage = "تمت إضافة جهة الاتصال",
            unreadCount = 0,
            bio = "مستخدم جديد في تطبيق الحواري"
        )
        _users.update { listOf(newUser) + it }
        _messages.update { currentMap ->
            currentMap + (newId to listOf(
                Message(
                    id = UUID.randomUUID().toString(),
                    senderId = newId,
                    receiverId = "me",
                    text = "مرحباً! لقد أضفتني إلى جهات اتصالك في تطبيق الحواري.",
                    timestamp = getCurrentTime(),
                    isFromMe = false
                )
            ))
        }
        return newUser
    }

    fun startCall(user: User, isVideo: Boolean) {
        _activeCall.value = ActiveCallState(
            isActive = true,
            user = user,
            isVideo = isVideo,
            isMuted = false,
            isSpeaker = isVideo
        )

        // Log call
        val record = CallRecord(
            id = UUID.randomUUID().toString(),
            userId = user.id,
            userName = user.name,
            userEmail = user.email,
            type = if (isVideo) CallType.VIDEO else CallType.VOICE,
            direction = CallDirection.OUTGOING,
            timestamp = "الآن",
            duration = "جارٍ..."
        )
        _calls.update { listOf(record) + it }
    }

    fun endCall() {
        _activeCall.value = ActiveCallState(isActive = false)
    }

    fun toggleMute() {
        _activeCall.update { it.copy(isMuted = !it.isMuted) }
    }

    fun toggleSpeaker() {
        _activeCall.update { it.copy(isSpeaker = !it.isSpeaker) }
    }
}
