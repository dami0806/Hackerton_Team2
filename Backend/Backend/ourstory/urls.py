from django.urls import path, include
from rest_framework import routers
from .views import * 

# viewset 라우터 설정
router = routers.DefaultRouter()
router.register(r'calendar', CalendarViewSet)

urlpatterns = [
    path('', include(router.urls)),
] 

