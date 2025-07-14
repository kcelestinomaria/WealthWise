# 🔧 Gradle Download Fix - Windows

## The Problem
`Could not install Gradle distribution` - common on Windows due to permissions, antivirus, or path issues.

## ✅ **Solution Steps (Try in Order)**

### **Step 1: Quick Permission Fix**
1. **Close Android Studio completely**
2. **Run Command Prompt as Administrator**
3. **Execute these commands:**
```cmd
cd C:\Users\User\.gradle
rmdir /s /q wrapper
rmdir /s /q caches
```
4. **Restart Android Studio**
5. **Try sync again**

### **Step 2: Check Antivirus**
- **Temporarily disable antivirus** (especially Windows Defender real-time protection)
- **Add exclusions for:**
  - `C:\Users\User\.gradle\`
  - `C:\Users\User\AndroidStudioProjects\`
  - Android Studio installation folder

### **Step 3: Use Local Gradle (Recommended)**
1. **In Android Studio:**
   - `File` → `Settings` → `Build, Execution, Deployment` → `Gradle`
   - **Change "Use Gradle from" to:** `Gradle wrapper`
   - **OR use bundled Gradle:** Select `wrapper` option

2. **Alternative - Use Project Gradle:**
   - Select `Specified location`
   - Point to: `C:\Program Files\Android\Android Studio\gradle\gradle-8.0\`

### **Step 4: Manual Gradle Download**
If automatic download fails:

1. **Download manually:**
   - Go to: https://gradle.org/releases/
   - Download: `gradle-8.0-bin.zip`

2. **Extract to:**
   ```
   C:\Users\User\.gradle\wrapper\dists\gradle-8.0-bin\[hash]\
   ```

3. **Extract the zip** so you have:
   ```
   C:\Users\User\.gradle\wrapper\dists\gradle-8.0-bin\[hash]\gradle-8.0\
   ```

### **Step 5: Use Different Gradle Home**
1. **Create new Gradle directory:**
   ```cmd
   mkdir C:\gradle
   ```

2. **In Android Studio Settings:**
   - `File` → `Settings` → `Build` → `Gradle`
   - **Gradle user home:** `C:\gradle`

### **Step 6: Network/Proxy Issues**
If behind corporate firewall:

1. **Add to gradle.properties:**
   ```properties
   systemProp.http.proxyHost=your-proxy-host
   systemProp.http.proxyPort=proxy-port
   systemProp.https.proxyHost=your-proxy-host
   systemProp.https.proxyPort=proxy-port
   ```

2. **OR use Android Studio proxy settings:**
   - `File` → `Settings` → `HTTP Proxy`

## 🚨 **Emergency Workaround**

If nothing else works, use **Local Development Mode:**

### **Option A: Use Android Studio's Bundled Gradle**
1. `File` → `Settings` → `Build` → `Gradle`
2. Select: **"Use bundled Gradle"**
3. **Restart Android Studio**

### **Option B: Downgrade Gradle Version**
I've already updated the project to use **Gradle 8.0** (more stable). 

If still failing, further downgrade to **Gradle 7.6**:

In `gradle/wrapper/gradle-wrapper.properties`:
```properties
distributionUrl=https\://services.gradle.org/distributions/gradle-7.6-bin.zip
```

## 🎯 **Expected Success**

After applying fixes:
```
✅ BUILD SUCCESSFUL in 2m 30s
✅ Gradle sync finished
✅ App runs on emulator
```

## 🆘 **Still Not Working?**

### **Nuclear Option - Clean Reinstall:**
```cmd
# 1. Uninstall Android Studio
# 2. Delete these folders:
rmdir /s "C:\Users\User\.android"
rmdir /s "C:\Users\User\.gradle" 
rmdir /s "C:\Users\User\AndroidStudioProjects"

# 3. Reinstall Android Studio
# 4. Import project fresh
```

### **Alternative IDEs:**
- **IntelliJ IDEA Community** (free, often handles Gradle better)
- **VS Code** with Android extensions

---

## 🔍 **Root Cause Analysis**

**Most Common Causes:**
1. **Windows path length limits** (older Windows versions)
2. **Antivirus blocking** `.gradle` folder writes  
3. **Corporate firewall** blocking `services.gradle.org`
4. **Permission issues** in user directory
5. **Corrupted Gradle cache** from previous failed downloads

**Why This Happens:**
- Gradle downloads large distribution files
- Windows has strict path/permission rules
- Antivirus sees repeated network downloads as suspicious
- Corporate networks often block build tool downloads

---

**🎯 The project will work once Gradle downloads successfully!** 