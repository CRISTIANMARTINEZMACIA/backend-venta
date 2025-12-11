#!/usr/bin/env bash
set -e

BASE_DIR="src/main/java/com/cristianmartinez/api/backendventa/dto"
REQ_DIR="$BASE_DIR/request"
RES_DIR="$BASE_DIR/response"

mkdir -p "$REQ_DIR" "$RES_DIR"

cat > "$RES_DIR/DefaultResponse.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DefaultResponse<T> {
    private int code;
    private String status;
    private String message;
    private T body;
}
EOF

cat > "$REQ_DIR/ProductRequest.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private Long categoryId;
}
EOF

cat > "$RES_DIR/ProductResponse.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private Long categoryId;
}
EOF

cat > "$REQ_DIR/ClientRequest.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String documentNumber;
    private String email;
    private String phone;
    private String address;
}
EOF

cat > "$RES_DIR/ClientResponse.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String documentNumber;
    private String email;
    private String phone;
    private String address;
}
EOF

cat > "$REQ_DIR/UserRequest.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private Long id;
    private String username;
    private String email;
    private String password;
    private Long rolId;
    private Long pointSaleId;
}
EOF

cat > "$RES_DIR/UserResponse.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private Long rolId;
    private Long pointSaleId;
}
EOF

cat > "$REQ_DIR/CategoryRequest.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequest {
    private Long id;
    private String name;
    private String description;
}
EOF

cat > "$RES_DIR/CategoryResponse.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;
}
EOF

cat > "$REQ_DIR/IngredientRequest.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientRequest {
    private Long id;
    private String name;
    private String unit;
    private Double quantity;
}
EOF

cat > "$RES_DIR/IngredientResponse.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientResponse {
    private Long id;
    private String name;
    private String unit;
    private Double quantity;
}
EOF

cat > "$REQ_DIR/StockRequest.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockRequest {
    private Long id;
    private Long productId;
    private Integer quantity;
    private String location;
}
EOF

cat > "$RES_DIR/StockResponse.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockResponse {
    private Long id;
    private Long productId;
    private Integer quantity;
    private String location;
}
EOF

cat > "$REQ_DIR/RecipeRequest.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeRequest {
    private Long id;
    private Long productId;
    private List<Long> ingredientIds;
    private String instructions;
}
EOF

cat > "$RES_DIR/RecipeResponse.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeResponse {
    private Long id;
    private Long productId;
    private List<Long> ingredientIds;
    private String instructions;
}
EOF

cat > "$REQ_DIR/InventoryMovementRequest.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryMovementRequest {
    private Long id;
    private Long productId;
    private Integer quantity;
    private String type;
    private LocalDateTime movementDate;
    private String note;
}
EOF

cat > "$RES_DIR/InventoryMovementResponse.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryMovementResponse {
    private Long id;
    private Long productId;
    private Integer quantity;
    private String type;
    private LocalDateTime movementDate;
    private String note;
}
EOF

cat > "$REQ_DIR/BusinessRequest.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessRequest {
    private Long id;
    private String name;
    private String taxId;
    private String address;
    private String phone;
    private String email;
}
EOF

cat > "$RES_DIR/BusinessResponse.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessResponse {
    private Long id;
    private String name;
    private String taxId;
    private String address;
    private String phone;
    private String email;
}
EOF

cat > "$REQ_DIR/SaleRequest.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleRequest {
    private Long id;
    private Long clientId;
    private Long pointSaleId;
    private Double total;
    private LocalDateTime saleDate;
    private List<SaleDetailRequest> details;
}
EOF

cat > "$RES_DIR/SaleResponse.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleResponse {
    private Long id;
    private Long clientId;
    private Long pointSaleId;
    private Double total;
    private LocalDateTime saleDate;
    private List<SaleDetailResponse> details;
}
EOF

cat > "$REQ_DIR/SaleDetailRequest.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDetailRequest {
    private Long id;
    private Long saleId;
    private Long productId;
    private Integer quantity;
    private Double price;
}
EOF

cat > "$RES_DIR/SaleDetailResponse.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDetailResponse {
    private Long id;
    private Long saleId;
    private Long productId;
    private Integer quantity;
    private Double price;
}
EOF

cat > "$REQ_DIR/PermissionRequest.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionRequest {
    private Long id;
    private String name;
    private String description;
}
EOF

cat > "$RES_DIR/PermissionResponse.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionResponse {
    private Long id;
    private String name;
    private String description;
}
EOF

cat > "$REQ_DIR/ViewRequest.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewRequest {
    private Long id;
    private String name;
    private String path;
}
EOF

cat > "$RES_DIR/ViewResponse.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewResponse {
    private Long id;
    private String name;
    private String path;
}
EOF

cat > "$REQ_DIR/ViewPermissionRequest.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewPermissionRequest {
    private Long id;
    private Long viewId;
    private Long permissionId;
}
EOF

cat > "$RES_DIR/ViewPermissionResponse.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewPermissionResponse {
    private Long id;
    private Long viewId;
    private Long permissionId;
}
EOF

cat > "$REQ_DIR/RolRequest.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolRequest {
    private Long id;
    private String name;
    private String description;
}
EOF

cat > "$RES_DIR/RolResponse.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolResponse {
    private Long id;
    private String name;
    private String description;
}
EOF

cat > "$REQ_DIR/RolPermissionRequest.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolPermissionRequest {
    private Long id;
    private Long rolId;
    private Long permissionId;
}
EOF

cat > "$RES_DIR/RolPermissionResponse.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolPermissionResponse {
    private Long id;
    private Long rolId;
    private Long permissionId;
}
EOF

cat > "$REQ_DIR/PointSaleRequest.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointSaleRequest {
    private Long id;
    private String name;
    private String location;
}
EOF

cat > "$RES_DIR/PointSaleResponse.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointSaleResponse {
    private Long id;
    private String name;
    private String location;
}
EOF

cat > "$REQ_DIR/SaleSummaryRequest.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleSummaryRequest {
    private LocalDateTime from;
    private LocalDateTime to;
    private Long pointSaleId;
}
EOF

cat > "$RES_DIR/SaleSummaryResponse.java" <<'EOF'
package com.cristianmartinez.api.backendventa.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleSummaryResponse {
    private LocalDateTime from;
    private LocalDateTime to;
    private Long pointSaleId;
    private Double totalSales;
    private Integer totalItems;
}
EOF

echo "DTO files generated under $BASE_DIR"