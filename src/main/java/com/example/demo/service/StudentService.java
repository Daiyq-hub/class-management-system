@Service
public class StudentService {
    @Autowired
    private StudentRepository repo;
    
    private Map<String, String> userRoleMap = new HashMap<>(); // <userId, role>
    
    @PostConstruct
    public void init() {
        // 从DB加载角色到Map，例如
        userRoleMap.put("user1", "admin");
        // 实际从UserRepository加载
    }
    
    public Student addStudent(Student student) {
        return repo.save(student);
    }
    
    // 其他CRUD方法...
    
    // 反射调用示例
    public Object dynamicInvoke(String methodName, Object... args) throws Exception {
        Method method = this.getClass().getMethod(methodName, getArgTypes(args));
        return method.invoke(this, args);
    }
    
    private Class<?>[] getArgTypes(Object[] args) {
        // 简化处理，实际需匹配类型
        return new Class[]{Student.class}; // 示例
    }
    
    // 使用Map检查角色
    public boolean isAdmin(String userId) {
        return "admin".equals(userRoleMap.get(userId));
    }
}