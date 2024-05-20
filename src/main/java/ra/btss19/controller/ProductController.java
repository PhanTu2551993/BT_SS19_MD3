package ra.btss19.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ra.btss19.dto.ProductDTO;
import ra.btss19.entity.Category;
import ra.btss19.entity.Product;
import ra.btss19.service.ICategoryService;
import ra.btss19.service.IProductService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = {"/Product"})
public class ProductController {
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;
    @Value("${file-upload}")
    private String fileUpload;
    @GetMapping
    public String productHome(Model model){
        List<Product> categories = productService.getProduct();
        model.addAttribute("product",categories);
        return "/productHome";
    }
    @GetMapping("/createProduct")
    public String createProduct(Model model) {
        model.addAttribute("product", new ProductDTO());
        model.addAttribute("categories", categoryService.getCategory());
        return "createProduct";
    }

    @PostMapping("/saveProduct")
    public String actionCreateProduct(@Valid @ModelAttribute("product") ProductDTO productDTO, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors())
        {
            model.addAttribute("product", productDTO);
            model.addAttribute("categories", categoryService.getCategory());
            return "/createProduct";
        }
        Product product = Product.builder()
                .proId(productDTO.getProId())
                .proName(productDTO.getProName())
                .price(productDTO.getPrice())
                .stock(productDTO.getStock())
                .proStatus(productDTO.getProStatus())
                .cat(categoryService.getCategoryById(productDTO.getCatId()))
                .build();

        //Lấy đường dẫn tương đối từ thư mục của project đến thư muục images trong project
        String path = request.getServletContext().getRealPath("images");
        //Khởi 1 đối tượng File theo đường dẫn tương đối sẽ lấy được đường dẫn tuyệt đối
        File file1 = new File(path);

        MultipartFile imgFile = productDTO.getProImage();
        //lấy tên file file cần upload lên
        String fileName = imgFile.getOriginalFilename();
        try {
            //Khởi tạo đường dẫn của file sẽ copy lên
            File destination = new File(file1.getAbsolutePath()+"/"+fileName);
            if(!destination.exists())
                FileCopyUtils.copy(imgFile.getBytes(),destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Gán lại tên file vào biến imageName của Entity
        product.setProImage(fileName);
        //Save dữ liệu vào database
        boolean bl = productService.insertProduct(product);
        if(bl){
            return "redirect:/Product";
        }else {
            model.addAttribute("p",product);
            return "/createProduct";
        }
//        MultipartFile file = productDTO.getProImage();
//        String fileName = file.getOriginalFilename();
//        String filePath = fileUpload + fileName;
//
//        try {
//            file.transferTo(new File(filePath));
//        } catch (IOException e) {
//            e.printStackTrace();
//            model.addAttribute("errorMessage", "Error uploading file");
//            return "/createProduct";
//        }
//        Product product = Product.builder()
//                .proId(productDTO.getProId())
//                .proImage(fileName)
//                .proName(productDTO.getProName())
//                .price(productDTO.getPrice())
//                .stock(productDTO.getStock())
//                .proStatus(productDTO.getProStatus())
//                .cat(categoryService.getCategoryById(productDTO.getCatId()))
//                .build();
//        productService.insertProduct(product);
//        return "redirect:/Product";
    }
    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productService.deleteProduct(id);
        return "redirect:/Product";
    }
    @GetMapping("/editProduct/{id}")
    public String editProduct(@PathVariable int id,@ModelAttribute("product") ProductDTO productDTO, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("product", productDTO);
        model.addAttribute("categories", categoryService.getCategory());
        return "/editProduct";
    }
    @PostMapping("/updateProduct")
    public String updateProduct(Product product) {
        productService.updateProduct(product);
        return "redirect:/Product";
    }
    @GetMapping("/viewProduct/{id}")
    public String viewProduct(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "/viewProduct";
    }
}
