package com.itec0401.backend.domain.clothing.entity.type;

import java.util.*;
import java.util.stream.Collectors;

public enum Category {

    // 최상위 카테고리
    ROOT("카테고리", null),
        // 상의 (Tops)
        TOPS("상의", ROOT),
            SHORT_SLEEVE_TSHIRT("반소매 티셔츠", TOPS),
            LONG_SLEEVE_TSHIRT("긴팔 티셔츠", TOPS),
            SLEEVELESS("민소매", TOPS),
            POLO_SHIRT("카라티", TOPS),
            TANK_TOP("탱크탑", TOPS),
            CROP_TOP("크롭탑", TOPS),
            BLOUSE("블라우스", TOPS),
            LONG_SLEEVE_SHIRT("긴팔셔츠", TOPS),
            SHORT_SLEEVE_SHIRT("반팔셔츠", TOPS),
            CREW_NECK_SWEATER("맨투맨", TOPS),
            HOODIE("후드", TOPS),
            KNITWEAR("니트", TOPS),
            KNIT_VEST("니트조끼", TOPS),
            SPORTS_TOP("스포츠상의", TOPS),
            BODYSUIT("바디수트", TOPS),
        // 원피스 (Dresses)
        DRESSES("원피스", ROOT),
            CASUAL_DRESS("캐주얼 원피스", DRESSES),
            TSHIRT_DRESS("티셔츠 원피스", DRESSES),
            SHIRT_DRESS("셔츠 원피스", DRESSES),
            HOODED_DRESS("후드 원피스", DRESSES),
            KNIT_DRESS("니트 원피스", DRESSES),
            JACKET_DRESS("자켓 원피스", DRESSES),
            DUNGAREE_DRESS("멜빵 원피스", DRESSES),
            JUMPSUIT("점프수트", DRESSES),
            EVENING_DRESS("이브닝 원피스", DRESSES),
            MINI_DRESS("미니 원피스", DRESSES),
        // 바지 (Pants)
        PANTS("바지", ROOT),
            JEANS("청바지", PANTS),
            LONG_PANTS("긴바지", PANTS),
            DRESS_PANTS("정장바지", PANTS),
            ATHLETIC_PANTS("운동복", PANTS),
            LEGGINGS("레깅스", PANTS),
            SHORTS("반바지", PANTS),
        // 치마 (Skirts)
        SKIRTS("치마", ROOT),
            MINI_SKIRT("미니스커트", SKIRTS),
            MIDI_SKIRT("미디스커트", SKIRTS),
            LONG_SKIRT("롱스커트", SKIRTS),
        // 아우터 (Outerwear)
        OUTERWEAR("아우터", ROOT),
            COAT("코트", OUTERWEAR),
            TRENCH_COAT("트렌치", OUTERWEAR),
            FUR_COAT("털코트", OUTERWEAR),
            MOUTON_COAT("무스탕", OUTERWEAR),
            BLAZER("블레이저", OUTERWEAR),
            JACKET("자켓", OUTERWEAR),
            BOMBER_JACKET("블루종", OUTERWEAR),
            BASEBALL_JACKET("야구잠바", OUTERWEAR),
            TRUCKER_JACKET("트러커", OUTERWEAR),
            RIDER_JACKET("라이더자켓", OUTERWEAR),
            CARDIGAN("가디건", OUTERWEAR),
            ZIP_UP_JACKET("집업", OUTERWEAR),
            MILITARY_JACKET("야상", OUTERWEAR),
            SPORTS_OUTERWEAR("스포츠아우터", OUTERWEAR),
            FLEECE("후리스", OUTERWEAR),
            PARKA("파카", OUTERWEAR),
            LIGHTWEIGHT_PADDED_JACKET("경량패딩", OUTERWEAR),
            PADDED_JACKET("패딩", OUTERWEAR),
            VEST("조끼", OUTERWEAR),
        // 모자 (Hats)
        HATS("모자", ROOT),
            CAP("캡", HATS),
            HAT("햇", HATS),
            BEANIE("비니", HATS),
            BERET("베레모", HATS),
            FEDORA("페도라", HATS),
            SUN_HAT("썬햇", HATS),
        // 신발 (Shoes)
        SHOES("신발", ROOT),
            SNEAKERS("스니커즈", SHOES),
            SLIP_ON("슬립온", SHOES),
            ATHLETIC_SHOES("운동화", SHOES),
            HIKING_BOOTS("등산화", SHOES),
            BOOTS("부츠", SHOES),
            WORKER_BOOTS("워커", SHOES),
            UGG_BOOTS("어그부츠", SHOES),
            LOAFERS("로퍼", SHOES),
            BOAT_SHOES("보트", SHOES),
            FLATS("플랫슈즈", SHOES),
            HEELS("힐", SHOES),
            SANDALS("샌들", SHOES),
            SANDAL_HEELS("샌들힐", SHOES),
            SLIPPERS("슬리퍼", SHOES),
            MULE_HEELS("뮬힐", SHOES),
        // 가방 (Bags)
        BAGS("가방", ROOT),
            TOTE_BAG("토트백", BAGS),
            SHOULDER_BAG("숄더백", BAGS),
            CROSSBODY_BAG("크로스백", BAGS),
            WAIST_BAG("웨이스트백", BAGS),
            ECO_BAG("에코백", BAGS),
            BACKPACK("백팩", BAGS),
            BOSTON_BAG("보스턴백", BAGS),
            CLUTCH_BAG("클러치백", BAGS),
            DOCUMENT_BAG("서류가방", BAGS),
            DUFFLE_BAG("짐색", BAGS),
            LUGGAGE("캐리어", BAGS),
    INVALID("예외", null);

    // 카테고리 이름
    private final String title;
    // 부모 카테고리
    private final Category parentCategory;
    // 자식카테고리
    private final List<Category> childCategories;

    Category(String title, Category parentCategory) {
        this.title = title;
        this.parentCategory = parentCategory;
        this.childCategories = new ArrayList<>();
        if(Objects.nonNull(parentCategory)) parentCategory.childCategories.add(this);
    }

    public String getTitle() {
        return title;
    }

    // 부모카테고리 Getter (Name 반환하도록 수정했음)
    public String getParentCategoryName() {
        Optional<Category> category = Optional.ofNullable(parentCategory);
        return category.map(Enum::name).orElse("INVALID");
    }

    // 자식카테고리 Getter
    public List<Category> getChildCategories() {
        return Collections.unmodifiableList(childCategories);
    }

    // 마지막 카테고리인지 반환
    public boolean isLeafCategory() {
        return childCategories.isEmpty();
    }

    // 마지막 카테고리들 반환
    public List<Category> getLeafCategories() {
        return Arrays.stream(Category.values())
                .filter(category -> category.isLeafCategoryOf(this))
                .collect(Collectors.toList());
    }

    private boolean isLeafCategoryOf(Category category) {
        return this.isLeafCategory() && category.contains(this);
    }

    private boolean contains(Category category) {
        if(this.equals(category)) return true;

        return Objects.nonNull(category.parentCategory) && this.contains(category.parentCategory);
    }

    public static Category convertString(String title) {
        for (Category category : Category.values()) {
            if(Objects.equals(category.name().toLowerCase(), title.toLowerCase())) return category;
        }
        return INVALID;
    }
}