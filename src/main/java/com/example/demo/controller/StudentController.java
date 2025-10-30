@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService service;
    
    @PostMapping
    public Student add(@RequestBody Student student) {
        return service.addStudent(student);
    }
    
    // 其他 endpoints...
    
    // 反射 endpoint (管理员专用)
    @PostMapping("/dynamic")
    public Object dynamicCall(@RequestParam String method, @RequestBody Student student) throws Exception {
        return service.dynamicInvoke(method, student);
    }
}